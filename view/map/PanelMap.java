package view.map;

import controller.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import view.map.TopPanel;
import view.info.GraphShip;
import view.info.MarkX;

/**
 *
 * @author Andres santiaog
 * @since 24-05-2019
 * @version 2.0
 */

public class PanelMap extends JPanel {
    
    private ArrayList<GraphShip> listSongs;
    private ArrayList<MarkX> listMarks;
    //private BtnCell[][]tableCells;
    
    private int porcentX;
    private int porcentY;
    //esta es la distancia en pixeles extre cuadros
    private int separationX;
    private int separationY;

    // IMPORTANTE
    private int growNumberX = 1;
    private int divitionsX = 13;

    //cambiar a double si hace falta
    private int growNumberY = 1;
    private int divitionsY = 8;

    private TopPanel topPanel;
    
    public PanelMap(int divitionsX, int divitionsY, MouseListener mouse, ActionListener action) {
        setBackground(Color.WHITE);
        
        this.divitionsY = divitionsY;
        this.divitionsX = divitionsX;
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        setLayout(new BorderLayout());
        
        
        
        this.addMouseListener(mouse);
        //tableCells = new BtnCell[divitionsX][divitionsY];
        listSongs = new ArrayList<>();
        listMarks = new ArrayList<>();
        
        
        
    }
    
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D g2 = (Graphics2D) graphics;
        Image img = new ImageIcon(getClass().getResource(Constants.oldpaper)).getImage();
        graphics.drawImage(img, 0, 0, null);
        
        porcentX = (this.getWidth());
        porcentY = (this.getHeight());
        
        graphics.setColor(Color.BLACK);
        paintLineX(graphics);
        paintLineY(graphics);
        paintSeparationsX(graphics);
        paintSeparationsY(graphics);
        
        drawShips(listSongs, graphics);
        drawMarks(listMarks, graphics);
        //drawBar(1,7,"rock", graphics);
        //g.fillRoundRect (250, 270, 50, 70, 6, 6);
        //fillRect (150, 70, 50, 70);
        //drawLinePlane(1.2, 3.5, 4.0, 4.0, graphics);
    }

    //este es para el array que se va a graficar en barras, se llama en el controller
    public void setShips(ArrayList<GraphShip> listSongs) {
        
        
        this.listSongs = listSongs;
        repaint();
        revalidate();
    }
    
    public void setListMarks(ArrayList<MarkX> listMarks) {
        this.listMarks = listMarks;
    }
    
    public void paintLineX(Graphics graphics) {
        //xa es el borde
        int xa = porcentX * 10 / 100;
        int ya = porcentY - porcentY * 12 / 100;
        int xb = porcentX - xa;
        int yb = porcentY - porcentY * 12 / 100;
        
        graphics.setColor(Color.BLACK);
        graphics.drawLine(xa, ya, xb, yb);
        
    }
    
    public void paintLineY(Graphics graphics) {
        int xa = porcentX * 10 / 100;
        int ya = porcentY - porcentY * 12 / 100;
        int xb = porcentX * 10 / 100;
        int yb = porcentY - ya;
        
        graphics.setColor(Color.BLACK);
        graphics.drawLine(xa, ya, xb, yb);
    }

    //este onta las linea de separacion
    public void paintSeparationsX(Graphics graphics) {
        int xab = porcentX * 10 / 100;
        int ya = porcentY - porcentY * 12 / 100;
        
        int yb = porcentY - ya;
        
        separationX = ((porcentX - porcentX * 10 / 100) - porcentX * 10 / 100) / divitionsX;
        for (int i = 0; i < divitionsX; i++) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(xab + (separationX * (i + 1)), ya, xab + (separationX * (i + 1)), yb);
            graphics.drawString("" + (i + 1) * growNumberX, xab + (separationX * (i) + separationX / 2), (porcentY - porcentY * 9 / 100));
        }
        
    }
    
    public void paintSeparationsY(Graphics graphics) {
        int xa = porcentX * 10 / 100;
        int yab = porcentY - porcentY * 12 / 100;
        int xb = porcentX - xa;
        
        separationY = ((porcentY - porcentY * 12 / 100) - porcentY * 12 / 100) / divitionsY;
        
        for (int i = 0; i < divitionsY; i++) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(xa, (yab - (separationY * (i + 1))), xb, (yab - (separationY * (i + 1))));
            graphics.drawString("" + (i + 1) * growNumberY, (xa - (xa / (divitionsY) * 5)), (yab - (separationY * (i)) - separationY / 2));
            
        }
        
    }

    //dibuja una linea con usando coordenasa cartecianas xa ya y xb yb
    private void drawLinePlane(double xLineA, double yLineA, double xLineB, double yLineB, Graphics graph) {
        
        int unitTableX = divitionsX * growNumberX;        
        int unitTableY = divitionsY * growNumberY;        
        
        graph.setColor(Color.GREEN);
        graph.drawLine(getXPixelsGraph(xLineA), getYPixelsGraph(yLineA),
                (getXPixelsGraph(xLineB)), getYPixelsGraph(yLineB));
        
    }
    //este metodo recibe las coordenadas en Y de y lo conviene en pixeles que dan justo en la coordenada de la tabla
    
    private int getYPixelsGraph(double yLine) {
        int pixel = 0;
        pixel = (porcentY - porcentY * 12 / 100) - (int) ((yLine / (double) growNumberY) * separationY);        
        return pixel;        
    }
    
    private int getXPixelsGraph(double xLine) {
        int pixel = 0;
        pixel = (porcentX * 10 / 100) + (int) ((xLine / (double) growNumberX) * separationX);
        return pixel;        
    }
    
    public double getPostTableX(int pixi) {
        return (double) (pixi - porcentX * 10 / 100) / separationX + 1;
    }
    
    public double getPostTableY(int pixi) {
        int ya = porcentY - porcentY * 12 / 100;
        return (((double) divitionsY) - (double) (pixi - ya) / separationY) - divitionsY + 1;
    }

    /*
    
    
    este metodo dibuja las naves gusdadas en la lista de esta clase
    
     */
    public void drawShips(ArrayList<GraphShip> listShips, Graphics graph) {
        
        Image img1 = null;
        for (GraphShip ship : listShips) {
            //System.out.println("dibujando");
            Graphics2D g2 = (Graphics2D) graph;
            
            img1 = new ImageIcon(getClass().getResource(ship.getPhotoShip())).getImage();
            int Yinvert = ship.getyPos();
                Yinvert = Yinvert + (ship.getySize() - 1); 
            drawShip(g2, img1, ship.getxPos(), Yinvert, ship.getxSize(), ship.getySize());
        }
    }

    //esto dibuja una barra sobre el plano recibe la posicion de la fila, valor en y, nombre de la barra y graphics
    private void drawShip(Graphics graph, Image image, int xpos, int ypos, int xsize, int ysize) {
        int xposS = getXPixelsGraph(xpos - 1);
        int yposS = getYPixelsGraph(ypos);
        int xsizeS = xsize * separationX;
        int ysizeS = ysize * separationY;        
        
        graph.drawImage(image, xposS+1, yposS+1, xsizeS, ysizeS, this);
    }
    
    private void drawMarks(ArrayList<MarkX> listMarks, Graphics graphics) {
        BufferedImage img1 = null;
        for (MarkX mark : listMarks) {
            drawMark(graphics, mark.getXpos(), mark.getYpos(), mark.isState());
        }
        
    }

    //este dibuja una x individual
    private void drawMark(Graphics graph, int xpos, int ypos, boolean state) {
        int xposM = getXPixelsGraph(xpos - 1);
        int yposM = getYPixelsGraph(ypos);
        String link = Constants.xBlack;
        if (state == true) {
            link = Constants.xred;
        }
        Image img1 = null;
        
        Graphics2D g2 = (Graphics2D) graph;
        img1 = new ImageIcon(getClass().getResource(link)).getImage();
        //img1 = ImageIO.read(new File(link));
        graph.drawImage(img1, xposM, yposM, separationX, separationY, this);
    }    
}
