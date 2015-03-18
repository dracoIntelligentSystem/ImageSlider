import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.font.StrikeCache;



class myImage extends JFrame  implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon img[];
	private String imgPath[];
	private JLabel l, infoImg;
	private JButton prev,next, confirm, back;
	private int i,l1,size;
	private static int IMG_WIDTH=321;
	private static int IMG_HEIGHT=470;
	private JPanel Principal,slider,button;
	private String path = "C:\\Users\\dragoSI\\git\\ImageSlider\\immagini\\", emoFolder;
	private HashMap<String, HashMap<File, ImageIcon>> immagini = new HashMap<String, HashMap<File, ImageIcon>>();


	public myImage(String emo) throws IOException
	{
		this.emoFolder=emo;
		immagini.put("test", new HashMap<File, ImageIcon>());
		resizeImage(path+emo+"\\");
		size=immagini.get("test").size();
		i=size;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLayout(new BorderLayout( ));
		setVisible(true);
		//setSize(500, 500);		
		
		//JPanel p=new JPanel(new FlowLayout());
		Principal=new JPanel();		
		Principal.setLayout(new BoxLayout(Principal, BoxLayout.Y_AXIS));
		{
			slider= new JPanel(new FlowLayout());

			prev=new JButton("<");
			prev.setSize(prev.getWidth(), 471);
			slider.add(prev);

			l = new JLabel();
			slider.add(l);

			next=new JButton(">");
			next.setSize(next.getWidth(), 471);
			slider.add(next);
		}
		img = new ImageIcon[size];
		imgPath = new String[size];
		int count=0;
		for(File fileOriginalImage: immagini.get("test").keySet())
		{
			img[count] = immagini.get("test").get(fileOriginalImage);
			imgPath[count] = fileOriginalImage.getAbsolutePath();
			count++;
		}
		
		
		JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		System.out.println(imgPath[0]);
		System.out.println(((imgPath[0].substring(imgPath[0].lastIndexOf("\\")+1))));
		infoImg = new JLabel((imgPath[0].substring(imgPath[0].lastIndexOf("\\")+1)).split("\\.")[0]);
		l.setIcon(img[0]);
		infoPanel.add(infoImg);
		
		{
			button = new JPanel(new FlowLayout());
			confirm = new JButton("CONFIRM");
			back = new JButton("BACK");
			button.add(confirm);
			button.add(back);
		}
		
		prev.addActionListener(this);
		next.addActionListener(this);
		confirm.addActionListener(this);
		back.addActionListener(this);
		Principal.add(slider);
		Principal.add(infoPanel);
		Principal.add(button);
		this.getContentPane().add(Principal);
		this.pack();
		this.setResizable(false);
	}

	//riarrangia dimensioni a quelle della finestra
	private void resizeImage(String path) throws IOException{
		File f = new File(path);
		File[] allImages = f.listFiles();
		for(File fileImage : allImages){
			Image originalImage = ImageIO.read(fileImage);
			BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
			g.dispose();
			immagini.get("test").put(fileImage, new ImageIcon(resizedImage));
			//String nomeEmo = fileImage.getName();
			
		}
		System.out.println();
	}
	public  void actionPerformed(ActionEvent e)
	{

		if(e.getSource()==prev)
		{
			i=(i-1) % size;
			l.setIcon(img[i]);
		}
		if(e.getSource()==next)
		{	
			i=(i+1)% size;
			l.setIcon(img[i]);
		}
		infoImg.setText((imgPath[i].substring(imgPath[i].lastIndexOf("\\")+1)).split("\\.")[0]);
		l.setIcon(img[i]);
	}


	public static void main(String args[]) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Inserisci l'emozione da ricercare: ");
		String emo = sc.nextLine();
		myImage i1 = new myImage(emo);
	}


}
