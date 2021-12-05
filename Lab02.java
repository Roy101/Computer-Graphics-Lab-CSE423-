package lab2;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class Lab02 implements GLEventListener{

    private GLU glu;
    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();


        //4
        DDA(gl,0,0,0,40);
        DDA(gl,0,0,40,0);
        DDA(gl,40,-40,40,40);
        //9
        DDA(gl,60,40,100,40);
        DDA(gl,60,0,60,40);
        DDA(gl,60,0,100,0);
        DDA(gl,60,-40,100,-40);
        DDA(gl,100,-40,100,40);

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


    public void DDA(GL2 gl, float x1, float y1, float x2, float y2) {

        gl.glPointSize(3.0f);
        gl.glColor3d(1, 0, 0);

        //write your own code
        float dx=x2-x1;
        float dy=y2-y1;
        float m=dy/dx;
        int flag = (int) Math.max(Math.abs(dy), Math.abs(dx));
        if(m>-1 && m<1){
            gl.glBegin(GL2.GL_POINTS);
            for (int i =0; i <flag ; i++) {
                x1++;
                int x=(int)x1;
                y1=y1+m;
                int y=Math.round(y1);
                gl.glVertex2d(x,y);
            }
               gl.glEnd();
        }
        else {
            gl.glBegin(GL2.GL_POINTS);
            for (int i = 0; i <flag ; i++) {
                y1++;
                int y=(int)y1;
                x1=x1+1/m;
                int x=Math.round(x1);
                gl.glVertex2d(x,y);
            }
              gl.glEnd();
        }

    }
    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Lab02 l = new Lab02();
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