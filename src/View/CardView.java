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

/**
 * CardView represents a UnoCard
 * Draws using Swing's paintComponent
 * It has a reference to a UnoCard to now what color to draw
 */
public class CardView extends JPanel {

	// The UNOCard-model this view represent
	private final UnoCard unoCardModel;

	// Cardsize in pixels
	private final static Dimension SIZE = new Dimension(100, 150);
	// Border that is set normally
	private Border defaultBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.white, Color.gray);
	// Border that it set when hovered
	private Border focusedBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.black, Color.gray);

	/**
	 * Creates a CardView
	 * @param unoCardModel the UnoCard that should be represented
	 * @param unoCardController, the controller that handle mouse events etc.
	 */
	public CardView(UnoCard unoCardModel, UNOCardController unoCardController) { // this is the future constructor for this viewUnoCard
		this.unoCardModel = unoCardModel;

		this.setPreferredSize(SIZE);
		this.setBorder(defaultBorder);

		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				setBorder(focusedBorder);
				unoCardController.cardHovered(e);
			}

			public void mouseExited(MouseEvent e){
				setBorder(defaultBorder);
				unoCardController.cardStopHovered(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				unoCardController.cardClicked(e, unoCardModel);
			}
		});
	}
	
	protected void paintComponent(Graphics g){

		// info needed to paint
		int cardWidth = SIZE.width;
		int cardHeight = SIZE.height;
		Color cardColor = unoCardModel.getColor();
		String value = unoCardModel.getValue();

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, cardWidth, cardHeight);
		
		int margin = 5;
		g2.setColor(cardColor);
		g2.fillRect(margin, margin, cardWidth-2*margin, cardHeight-2*margin);
		
		g2.setColor(Color.white);
		AffineTransform org = g2.getTransform();
		g2.rotate(45,cardWidth*3.0/4,cardHeight*3.0/4);

		g2.fillOval(0,cardHeight/4,cardWidth*3/5, cardHeight);
		g2.setTransform(org);		
		
		//Value in the center		
		Font defaultFont = new Font("Helvetica", Font.BOLD, cardWidth/2+5);		
		FontMetrics fm = this.getFontMetrics(defaultFont);
		int StringWidth = fm.stringWidth(value)/2;
		int FontHeight = defaultFont.getSize()/3;
		
		g2.setColor(cardColor);
		g2.setFont(defaultFont);
		g2.drawString(value, cardWidth/2-StringWidth, cardHeight/2+FontHeight);
		
		//Value in the corner
		defaultFont = new Font("Helvetica", Font.ITALIC, cardWidth/5);		

		g2.setColor(Color.white);
		g2.setFont(defaultFont);
		g2.drawString(value, 2*margin,5*margin);		
	}


	//todo: why is this needed?
	public Dimension getSize() {
		return SIZE;
	}
}
