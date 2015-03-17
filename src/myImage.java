import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



class myImage extends JFrame  implements ActionListener
{

	ImageIcon m[];
	JLabel l;
	JButton b,b1;
	int i,l1;
	static int IMG_WIDTH=450;
	static int IMG_HEIGHT=658;
	JPanel p;
	String path = "C:\\Copy\\eclipse\\workspace\\ImageSlide\\immagini\\";
	HashMap<String, ArrayList<BufferedImage>> immagini = new HashMap<String, ArrayList<BufferedImage>>();

	public myImage() throws IOException
	{
		resizeImage(path);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout( ));
		setVisible(true);
		setSize(500, 500);		
		JPanel p=new JPanel(new FlowLayout());
		b=new JButton("Pre");
		b1=new JButton("Next");
		p.add(b);
		p.add(b1);
		add(p,BorderLayout.SOUTH);
		b.addActionListener(this);
		b1.addActionListener(this);
		m = new ImageIcon[2];
		m[0] = new ImageIcon(path+"disgusto1.png");

		m[1] = new ImageIcon(path+"disgusto2.png");
		l = new JLabel();
		l.setBounds(400, 0, getWidth(), getHeight());
		add(l,BorderLayout.CENTER);
		l.setIcon(m[0]);
		//this.pack();
	}

	//riarrangia dimensioni a quelle della finestra
	private static void resizeImage(String path) throws IOException{
		File f = new File(path);
		File[] allImages = f.listFiles();
		for(File fileImage : allImages){
			BufferedImage originalImage = ImageIO.read(fileImage);
			BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, originalImage.getType());
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
			g.dispose();
			
			String nomeEmo = fileImage.getName();
			System.out.println();
		}

	}
	public  void actionPerformed(ActionEvent e)
	{

		if(e.getSource()==b)
		{
			if(i==0)
			{
				JOptionPane.showMessageDialog(null,"This is First Image");
			}
			else
			{
				i=i-1;
				l.setIcon(m[i]);
			}
		}
		if(e.getSource()==b1)
		{
			if(i==m.length-1)
			{
				JOptionPane.showMessageDialog(null,"This is LastImage");
			}
			else
			{
				i=i+1;
				l.setIcon(m[i]);
			}
		}

	}


	public static void main(String args[]) throws IOException
	{
		myImage i1 = new myImage();
	}


}
