package view;

import constantes.Constantes;
import herramientas.Contenedor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JPanel;

/**
 * Clase encargada de representar la carrera con un aspecto grafico
 *
 * @author Raul Cobos y Sergio Rodriguez
 */
public class PanelCarrera {

    //Atributos
    private Contenedor container;
    private JPanel panel;
    private int[] xAnt;
    private int[] yAnt;

    public PanelCarrera(Contenedor c) {
        container = c;
        xAnt = new int[Constantes.NUMERO_CICLISTAS];
        yAnt = new int[Constantes.NUMERO_CICLISTAS];
    }

    public JPanel getJPanel() {

        int[] a = getEkisMap();

        panel = new JPanel(null);

        Dimension d = new Dimension(a[a.length - 1] + 50, 250);

        a = getYsMap(panel);

        int mayor = 250 / 2;
        for (int i = 0; i < a.length; i++) {
            if (Math.abs(a[i]) > mayor) {
                mayor = Math.abs(a[i]);
            }
        }

        d.height = (mayor + 120) * 2;

        panel.setSize(d);
        panel.setPreferredSize(d);

        System.out.println(panel.getPreferredSize() + "\n" + panel.getSize());


        return panel;

    }

    public void pintarCiclistas(JPanel panelCarrera) {

        for (int i = 0; i < Constantes.NUMERO_CICLISTAS; i++) {

            int x = getXCyclist((int) Math.round(container.getListaCiclistas().get(i).getBici().getDistanciaRecorrida()), container.getListaCiclistas().get(i).getPendienteActual());
            int y = getYCyclist(container.getListaCiclistas().get(i).getPendienteActual(), x, panelCarrera);

            y = y - 5;

            //panelCarrera.getComponent(i).setLocation((x) - 16, (y) - 28);

            Graphics g = panelCarrera.getGraphics();

            g.setColor(panelCarrera.getBackground());

            g.drawOval(xAnt[i] - 16, yAnt[i] - 9, 6, 6);
            g.drawLine(xAnt[i] - 13, yAnt[i] - 6, xAnt[i] - 9, yAnt[i] - 6);
            g.drawLine(xAnt[i] - 13, yAnt[i] - 6, xAnt[i] - 8, yAnt[i] - 9);
            g.drawLine(xAnt[i] - 5, yAnt[i] - 6, xAnt[i] - 8, yAnt[i] - 9);
            g.drawOval(xAnt[i] - 10, yAnt[i] - 6, 2, 2);
            g.drawOval(xAnt[i] - 8, yAnt[i] - 9, 6, 6);
            g.drawLine(xAnt[i] - 5, yAnt[i] - 6, xAnt[i] - 6, yAnt[i] - 11);
            g.drawLine(xAnt[i] - 6, yAnt[i] - 11, xAnt[i] - 8, yAnt[i] - 11);

            g.drawString(i + "", xAnt[i], yAnt[i] - 11);

            paintMap(panelCarrera);

            //g.drawString("[" + i + "]oÔ", xAnt[i], yAnt[i]);
            g.setColor(Color.black);

            g.drawOval(x - 16, y - 9, 6, 6);
            g.drawLine(x - 13, y - 6, x - 9, y - 6);
            g.drawLine(x - 13, y - 6, x - 8, y - 9);
            g.drawLine(x - 5, y - 6, x - 8, y - 9);
            g.drawOval(x - 10, y - 6, 2, 2);
            g.drawOval(x - 8, y - 9, 6, 6);
            g.drawLine(x - 5, y - 6, x - 6, y - 11);
            g.drawLine(x - 6, y - 11, x - 8, y - 11);

            g.drawString(i + "", x, y - 11);

            //g.drawString("[" + i + "]oÔ", x - 16, y - 9);

            xAnt[i] = x;
            yAnt[i] = y;

        }
    }

    /**
     * Pinta el mapa de la carretera
     *
     * @param panelCarrera
     */
    public void paintMap(JPanel panelCarrera) {

        int x[] = getEkisMap();
        int y[] = getYsMap(panelCarrera);

        for (int i = 0; i < x.length - 1; i++) {
            panelCarrera.getGraphics().drawLine(x[i], y[i], x[i + 1], y[i + 1]);
            panelCarrera.getGraphics().drawLine(x[i], (y[i]) - 14, x[i + 1], (y[i + 1]) - 14);
        }

        panelCarrera.getComponent(0).setLocation(x[x.length - 1] - 10, y[y.length - 1] - 110);
    }

    /**
     * Pinta las curvas del mapa
     *
     * @param panelCarrera
     */
    public void pintarCurvas(JPanel panelCarrera) {

        double pendiente = 0;

        Iterator<Map.Entry<Double, Double>> it = container.getCurvas().getMapa().entrySet().iterator();

        Map.Entry<Double, Double> elem;

        Iterator<Map.Entry<Double, Double>> it2;

        Map.Entry<Double, Double> elem2;

        int i = 0;

        double puntoM;

        while (it.hasNext()) {

            it2 = container.getPendiolo().getMapa().entrySet().iterator();
            elem = it.next();

            puntoM = elem.getKey();

            while (it2.hasNext()) {
                elem2 = it2.next();
                if (elem2.getKey() <= elem.getKey()) {
                    pendiente = elem2.getValue();
                }
            }

            int x = getXCyclist((int) Math.round(puntoM), (int) Math.round(pendiente));
            int y = getYCyclist(pendiente, x, panelCarrera);

            DecimalFormat f = new DecimalFormat("#.##");

            String msj = f.format(elem.getValue()) + "m/s";

            panelCarrera.getComponent(i + 1).setLocation(x - 2, y + 2);
            panelCarrera.getGraphics().drawString(msj, x - 2, y + 30);

            i++;
        }

    }

    /**
     * Devuelve el valor de los puntos X del mapa
     *
     * @param p
     * @return
     */
    private int[] getEkisMap() {

        int[] ekis = new int[container.getPendiolo().getMapa().size()];

        Iterator<Map.Entry<Double, Double>> it = container.getPendiolo().getMapa().entrySet().iterator();

        Map.Entry<Double, Double> elem = it.next();

        ekis[0] = (int) Math.round(elem.getKey());
        double xAux = ekis[0];
        double pendPorcAux = elem.getValue();

        int i = 1;

        while (it.hasNext()) {

            elem = it.next();

            double angulo = Math.atan(Math.abs(pendPorcAux / 100));

            double x = Math.cos(angulo) * (elem.getKey() - xAux);

            ekis[i] = ekis[i - 1] + (int) Math.round(x);


            xAux = elem.getKey();
            pendPorcAux = elem.getValue();

            i++;


        }

        return ekis;
    }

    /**
     * Devuelve el valor de los puntos Y del mapa
     *
     * @param p
     * @return
     */
    private int[] getYsMap(JPanel p) {

        int[] ys = new int[container.getPendiolo().getMapa().size()];

        ys[0] = p.getHeight() / 2;

        Iterator<Map.Entry<Double, Double>> it = container.getPendiolo().getMapa().entrySet().iterator();

        Map.Entry<Double, Double> elem = it.next();

        double puntoAnterior = 0;
        double pendienteAnterior = elem.getValue();

        int i = 1;

        while (it.hasNext()) {

            elem = it.next();


            if (pendienteAnterior >= 0) {

                double angulo = Math.atan(Math.abs(pendienteAnterior / 100));

                double y = Math.sin(angulo) * (elem.getKey() - puntoAnterior);

                ys[i] = (ys[i - 1] - (int) Math.round(y));

            } else {

                double angulo = Math.atan(Math.abs(pendienteAnterior / 100));

                double y = Math.sin(angulo) * (elem.getKey() - puntoAnterior);

                ys[i] = (ys[i - 1] + (int) Math.round(y));

            }

            pendienteAnterior = elem.getValue();
            puntoAnterior = elem.getKey();

            i++;

        }


        return ys;
    }

    /**
     * Devuelve el valor de la x en el panel para pintar el ciclista
     *
     * @param id
     * @return
     */
    private int getXCyclist(int _x, double pendienteActual) {

        double pendienteActualParaElCiclista = pendienteActual;
        double hipotenusa = container.getPendiolo().getMapa().firstKey();
        int indicePendiente = -1;

        Iterator<Entry<Double, Double>> it = container.getPendiolo().getMapa().entrySet().iterator();

        while (it.hasNext()) {
            Entry<Double, Double> elem = it.next();

            if (elem.getKey() <= _x) {
                hipotenusa = _x - elem.getKey();
                indicePendiente++;
            }
        }

        double angulo = Math.atan(Math.abs(pendienteActualParaElCiclista / 100));

        int ejeXValor = (int) Math.round(Math.cos(angulo) * hipotenusa);

        int x[] = getEkisMap();


        return x[indicePendiente] + ejeXValor;
    }

    /**
     * Devuelve el valor de la y en el panel para pintar el ciclista
     *
     * @param id
     * @param x
     * @param p
     * @return
     */
    private int getYCyclist(double pendienteActual, int x, JPanel p) {

        int y;

        double angunlo = Math.atan(Math.abs(pendienteActual) / 100);

        int yMapa[] = getYsMap(p);
        int xMapa[] = getEkisMap();

        int indice = 0;
        for (int i = 0; i < xMapa.length; i++) {
            if (xMapa[i] <= x) {
                indice = i;
            }
        }

        double yAux = Math.tan(angunlo) * (x - xMapa[indice]);

        if (pendienteActual > 0) {

            y = yMapa[indice] - (int) Math.round(yAux);

        } else {

            y = yMapa[indice] + (int) Math.round(yAux);

        }

        return y;
    }
}
