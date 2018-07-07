import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class MainScreen extends JPanel implements Runnable {
    static JFrame frame = new JFrame("Wind Map");
    static MainScreen scene = new MainScreen();
    static Mapp map=new Mapp();
    Path2D.Double arrow = createArrow();
    double theta = 0;
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.rewind();
            map.setNeighborsTemp();
            map.setDirection();
            frame.repaint();
        }

    }



    private Path2D.Double createArrow() {
        int length = 20;
        int barb = 5;
        double angle = Math.toRadians(25);
        Path2D.Double path = new Path2D.Double();
        path.moveTo(-length/2, 0);
        path.lineTo(length/2, 0);
        double x = length/2 - barb*Math.cos(angle);
        double y = barb*Math.sin(angle);
        path.lineTo(x, y);
        x = length/2 - barb*Math.cos(-angle);
        y = barb*Math.sin(-angle);
        path.moveTo(length/2, 0);
        path.lineTo(x, y);
        return path;
    }
    @Override

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        for(int i = 0; i<=10; i++){
            for(int j=0; j<=10;j++){
                g.drawLine(i*40,j*40,400,j*40);
                g.drawLine(i*40,j*40,i*40,400);
            }
        }
        for(int i=0;i<map.cityList.size();i++){

            if(map.cityList.get(i).direction==0){
                g.setColor(Color.BLUE);
                g.drawOval(map.cityList.get(i).x,map.cityList.get(i).y,3,3);
            }
            else {
                drawArrow(g, map.cityList.get(i).x, map.cityList.get(i).y, map.cityList.get(i).direction);
            }
        }

    }

    void drawArrow(Graphics gr, int x, int y, int dir) {
        Graphics2D g2 = (Graphics2D)gr;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform at = AffineTransform.getTranslateInstance(x,y);
       // System.out.println("scale" + scale);
        theta = (dir-1)*(Math.PI/4);
        at.rotate(theta);
        at.scale(2.0, 2.0);
        Shape shape = at.createTransformedShape(arrow);

        g2.setColor(Color.blue);
        g2.draw(shape);
    }


    public static void main(String[]args){
        Thread thread = new Thread(new MainScreen());
        thread.start();
        frame.setSize(420,440);
        frame.add(scene);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //   frame.setResizable(false);
    }
}
