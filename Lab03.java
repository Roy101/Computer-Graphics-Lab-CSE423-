package Lab03;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class Lab03 implements GLEventListener {

    private GLU glu;

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        //6
        MidpointLine(gl, 0, 30, 30, 30);
        MidpointLine(gl, 0, -30, 0, 30);
        MidpointLine(gl, 0, 0, 30, 0);
        MidpointLine(gl, 0, -30, 30, -30);
        MidpointLine(gl, 30, -30, 30, 0);
        //2
        MidpointLine(gl, 45, 30, 75, 30);
        MidpointLine(gl, 75, 0, 75, 30);
        MidpointLine(gl, 45, 0, 75, 0);
        MidpointLine(gl, 45, -30, 45, 0);
        MidpointLine(gl, 45, -30, 75, -30);




    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glViewport(-100, -50, 50, 100);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }


    public void MidpointLine(GL2 gl, int x1, int y1, int x2, int y2) {

        gl.glPointSize(3.0f);
        gl.glColor3d(1, 0, 0);

        //write your own code
        int zone = findZone(x1, y1, x2, y2);
        int convertedZones1[] = convertZone0(zone, x1, y1);
        int convertedZones2[] = convertZone0(zone, x2, y2);

        x1 = convertedZones1[0];
        y1 = convertedZones1[1];
        x2 = convertedZones2[0];
        y2 = convertedZones2[1];

        int dx = x2 - x1;
        int dy = y2 - y1;

        int d = 2 * dy - dx;
        int nE = 2 * (dy - dx);
        int e = 2 * dy;

        int x = x1;
        int y = y1;
        
        while (x <= x2) {
            x++;
            if (d <=0 ) {
                d = d + e;
            }

            else {
                d = d + nE;
                y++;
            }
            int[]  OriginalZone = OrginalZone(zone, x, y);
            gl.glBegin(GL2.GL_POINTS);
            gl.glVertex2d(OriginalZone[0], OriginalZone[1]);

        }
        gl.glEnd();


    }

    private int[] OrginalZone(int zone, int X, int Y) {
        int array[] = new int[2];

        if(zone==1){
            array[0]=Y;
            array[1]=X;
        }
        else if (zone==2){
            array[0]=-Y;
            array[1]=X;
        }
        else if(zone==3){
            array[0]=-X;
            array[1]=Y;
        }
        else if(zone==4){
            array[0]=-X;
            array[1]=-Y;
        }
        else if(zone==5){
            array[0]=-Y;
            array[1]=-X;
        }
        else if(zone==6){
            array[0]=Y;
            array[1]=-X;
        }
        else if(zone==7){
            array[0]=X;
            array[1]=-Y;
        }
        else{
            array[0]=X;
            array[1]=Y;
        }
        return array;
    }


    private int[] convertZone0(int zone, int X, int Y){

        int array[] = new int[2];

      if(zone==1){
         array[0]=Y;
         array[1]=X;
      }
      else if (zone==2){
         array[0]=Y;
         array[1]=-X;
      }
      else if(zone==3){
         array[0]=-X;
         array[1]=Y;
      }
      else if(zone==4){
         array[0]=-X;
         array[1]=-Y;
      }
      else if(zone==5){
         array[0]=-Y;
          array[1]=-X;
      }
      else if(zone==6){
         array[0]=-Y;
         array[1]=X;
      }
      else if(zone==7){
         array[0]=X;
         array[1]=-Y;
      }
      else{
          array[0]=X;
          array[1]=Y;
      }
      return array;
    }

    private int findZone(int x1, int y1, int x2, int y2) {
        int zone = 0;
        int dy = y2 - y1;
        int dx = x2 - x1;

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx >= 0 && dy >= 0) {
                zone = 0;
            }

            else if (dx <= 0 && dy >= 0) {
                zone = 3;
            }

            else if (dx <= 0 && dy <= 0) {
                zone = 4;
            }

            else if (dx >= 0 && dy <= 0) {
                zone = 7;
            }

        }

        else {
            if (dx >= 0 && dy >= 0) {
                zone = 1;
            }

            else if (dx <= 0 && dy >= 0) {
                zone = 2;
            }

            else if (dx <= 0 && dy <= 0) {
                zone = 5;
            }

            else if (dx >= 0 && dy <= 0) {
                zone = 6;
            }
        }

        return zone;
    }
    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Lab03 l = new Lab03();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame ("straight Line");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }//end of main
}//end of classimport javax.media.opengl.GL2;
