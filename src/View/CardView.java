package View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Model.CardModel.UnoCard;
import Controller.UNOCardController;

public class CardView extends JPanel {

	private final static Dimension SIZE = new Dimension(100, 150);

	private UnoCard unoCardModel;

	public UnoCard getModelUnoCard() {
		return unoCardModel;
	}

	public CardView(UnoCard unoCardModel) { // this is the future constructor for this viewUnoCard
		this();
		this.unoCardModel = unoCardModel;
	}

	private Border defaultBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.white, Color.gray);
	private Border focusedBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.black, Color.gray);

	private UNOCardController unoCardController;

	private boolean listenerEnabled = true; //todo this is temp, used for card on the board

	public CardView(){

		this.setPreferredSize(SIZE);
		this.setBorder(defaultBorder);
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				setBorder(focusedBorder);
				if (listenerEnabled)
					unoCardController.cardHovered(e);
			}

			public void mouseExited(MouseEvent e){
				setBorder(defaultBorder);
				if (listenerEnabled)
					unoCardController.cardStopHovered(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Unocard clicked");
				if (listenerEnabled)
					unoCardController.cardClicked(e);
			}
		});
	}

	public void setUnoCardController(UNOCardController unoCardController) {
		this.unoCardController = unoCardController;
	}

	public void disableMouseListener() {
		listenerEnabled = false;
	}
	
	protected void paintComponent(Graphics g){

		// info needed to paint
		int cardWidth = SIZE.width;
		int cardHeight = SIZE.height;
		Color cardColor = getColor();
		String value = getValue();

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, cardWidth, cardHeight);
		
		int margin = 5;
		g2.setColor(cardColor);
		g2.fillRect(margin, margin, cardWidth-2*margin, cardHeight-2*margin);
		
		g2.setColor(Color.white);
		AffineTransform org = g2.getTransform();
		g2.rotate(45,cardWidth*3/4,cardHeight*3/4);		

		g2.fillOval(0,cardHeight*1/4,cardWidth*3/5, cardHeight);
		g2.setTransform(org);		
		
		//Value in the center		
		Font defaultFont = new Font("Helvetica", Font.BOLD, cardWidth/2+5);		
		FontMetrics fm = this.getFontMetrics(defaultFont);
		int StringWidth = fm.stringWidth(value)/2;
		int FontHeight = defaultFont.getSize()*1/3;
		
		g2.setColor(cardColor);
		g2.setFont(defaultFont);
		g2.drawString(value, cardWidth/2-StringWidth, cardHeight/2+FontHeight);
		
		//Value in the corner
		defaultFont = new Font("Helvetica", Font.ITALIC, cardWidth/5);		
		fm = this.getFontMetrics(defaultFont);
		StringWidth = fm.stringWidth(value)/2;
		FontHeight = defaultFont.getSize()*1/3;
		
		g2.setColor(Color.white);
		g2.setFont(defaultFont);
		g2.drawString(value, 2*margin,5*margin);		
	}
	
	public void setCardSize(Dimension newSize){
		this.setPreferredSize(newSize);
	}

	public Color getColor() {
		return unoCardModel.getColor();
	}


	public String getValue() {
		return unoCardModel.getValue();
	}

	public Dimension getSize() {
		return SIZE;
	}
}
