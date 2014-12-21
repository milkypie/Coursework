package CustomComponents;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Background extends GUIComponent{
	public Image backgroundImage;
	public Boolean RenderWithImage = true;
	
	public Background(Image initialImage){
		backgroundImage = initialImage;
	}
	
	public Boolean getRenderWithImage() {
		return RenderWithImage;
	}

	public void setRenderWithImage(Boolean renderWithImage) {
		RenderWithImage = renderWithImage;
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(Image backgroundImage) {
		if(backgroundImage!=null){
			this.backgroundImage = backgroundImage;
		}else{
			System.out.println("backgournd image is null");
			this.RenderWithImage = false;
		}
	}

	public void Draw(Graphics g) {
		
		if(RenderWithImage){
			if(backgroundImage!=null){
				g.drawImage(backgroundImage, 0, 0, Control.CardMain.ScreenWidth , Control.CardMain.ScreenHeight, 0, 0, backgroundImage.getWidth(null) , backgroundImage.getHeight(null), null);
			}else{
				System.out.println("backgroundImage is not properly defined");
			}
		}else{
			System.out.println("Drawing background base");
			g.setColor(Colour);
			g.fillRect(0, 0, Control.CardMain.ScreenWidth, Control.CardMain.ScreenHeight);
			
		}
	}

	public void Update() {
		
	}
}
