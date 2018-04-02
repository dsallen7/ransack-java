package com.allen.silo.ransack.display.ui;

import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.di.annotation.Autowired;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.ui.element.AbsoluteContainer;
import org.mini2Dx.ui.element.Image;

import com.allen.silo.ransack.character.Player;
import com.allen.silo.ransack.display.RansackAssetManager;
import com.allen.silo.ransack.main.accessories.Clock;
import com.allen.silo.ransack.utils.FileUtility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;

public class HeadsUpDisplay {
	
	@Autowired
	private RansackAssetManager assetManager;
	private AbsoluteContainer hudContainer;
	
	private Pixmap baseHudPixmap;
	private Pixmap temp;
	
	public HeadsUpDisplay(AbsoluteContainer hudContainer){
		this.hudContainer = hudContainer;
		baseHudPixmap = new Pixmap( FileUtility.loadUIImageFile("interface.png") );
		temp = new Pixmap(300, 233, Pixmap.Format.RGBA8888);
		assetManager = Mdx.di.getBean(RansackAssetManager.class);
	}
	
	public void updateStats(Player p){
		Image hudImage = getHudImage();
		TextureRegion tr = hudImage.getTextureRegion(Mdx.di.getBean(RansackAssetManager.class));
		temp.drawPixmap(baseHudPixmap, 0, 0);
		drawHPBar(p.getCurrentHP(), p.getMaxHP());
		drawMPBar(p.getCurrentMP(), p.getMaxMP());
		this.drawClock(p.getClock());
		((Image)hudContainer.getElementById("HUDImage")).setTexture(new Texture( temp ));
	}

	private Image getHudImage(){
		return (Image) getHudContainer().getElementById("HUDImage");
	}
	
	private void setHudImage(){
		//getHudContainer().getElementById("HUDImage")
	}
	
	private void drawHPBar(float current, float max){
		Pixmap healthBar = new Pixmap(91, 10, Pixmap.Format.RGBA8888);
		float healthPercent = current/max;
		if (healthPercent >= 0.75f){
			healthBar.setColor(Color.GREEN);	
		}else if (healthPercent < 0.75f && healthPercent >= 0.25f){
			healthBar.setColor(Color.YELLOW);
		}else if (healthPercent < 0.25f){
			healthBar.setColor(Color.RED);
		}
		int currentBarsize = (int)(91*healthPercent);
		healthBar.drawRectangle(0, 0, currentBarsize, 10);
		temp.drawPixmap(healthBar, 23, 163);
	}
	
	private void drawMPBar(float current, float max){
		Pixmap healthBar = new Pixmap(91, 10, Pixmap.Format.RGBA8888);
		healthBar.setColor(Color.BLUE);
		int currentBarsize = (int)(91*(current/max));
		healthBar.drawRectangle(0, 0, currentBarsize, 10);
		temp.drawPixmap(healthBar, 23, 181);
	}
	
	private void drawClock(Clock c){
		float secs = c.getSecs();
		float mins = c.getMins();
		float hrs = c.getHours();
		int x = 35;
		int y = 117;
		double sRad = Math.toRadians(360*( secs / 60 ));
		double mRad = Math.toRadians(360*( mins / 60 ));
		double hRad = Math.toRadians(360*( hrs / 60 ));
		temp.setColor(Color.BLACK);
		temp.drawLine(x, y, 
				(int)(x + 14*Math.sin(sRad)), 
				(int)(y - 14*Math.cos(sRad)));
		temp.drawLine(x, y, 
				(int)(x + 12*Math.sin(mRad)), 
				(int)(y - 12*Math.cos(mRad)));
		temp.drawLine(x, y, 
				(int)(x + 19*Math.sin(hRad)), 
				(int)(y - 19*Math.cos(hRad)));
	}
	
	public void printLog(Graphics g, String[] logHistory){
		BitmapFont f = assetManager.get("steelfis.ttf");
		g.setFont(f);
		if(logHistory[0] != null)
			g.drawString(logHistory[0], 24, 372);
		if(logHistory[1] != null)
			g.drawString(logHistory[1], 24, 348);
		if(logHistory[2] != null)
			g.drawString(logHistory[2], 24, 324);
		/*
		Pixmap logWindow = new Pixmap(252, 73, Pixmap.Format.RGBA8888);
		// Copy  messages 1 + 2 in position 2 + 3
		temp.drawPixmap(temp, 0, 0, 0, 24, 252, 48);
		BitmapFont f = assetManager.get("steelfis.ttf");
		TextureRegion tr = this.renderToTexture(f, logMessage);
		
		temp.drawPixmap(this.renderToPixmap(f, logMessage), 0, 0);
		Texture t = new Texture(temp);
		*/
	}

	public AbsoluteContainer getHudContainer() {
		return hudContainer;
	}

	public void setHudContainer(AbsoluteContainer hudContainer) {
		this.hudContainer = hudContainer;
	}
	
    private TextureRegion renderToTexture(BitmapFont font, String text) {
        FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, 252, 24, false);
        SpriteBatch spriteBatch = new SpriteBatch();
        // Set up an ortho projection matrix
        Matrix4 projMat = new Matrix4();
        projMat.setToOrtho2D(0, 0, fbo.getWidth(), fbo.getHeight());
        spriteBatch.setProjectionMatrix(projMat);

        // Render the text onto an FBO
        fbo.begin();
        spriteBatch.begin();
        font.draw(spriteBatch, text, 0, 0);
        spriteBatch.end();
        fbo.end();

        // Flip the texture, and return it
        TextureRegion tex = new TextureRegion(fbo.getColorBufferTexture());
        tex.flip(false, true);
        return tex;
    }
	
    private Pixmap renderToPixmap(BitmapFont font, String text) {
        FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, 252, 24, false);
        SpriteBatch spriteBatch = new SpriteBatch();
        // Set up an ortho projection matrix
        Matrix4 projMat = new Matrix4();
        projMat.setToOrtho2D(0, 0, fbo.getWidth(), fbo.getHeight());
        spriteBatch.setProjectionMatrix(projMat);

        // Render the text onto an FBO
        fbo.begin();
        spriteBatch.begin();
        font.draw(spriteBatch, text, 0, 0);
        spriteBatch.end();
        fbo.end();

        // Flip the texture, and return it
        //TextureRegion tex = new TextureRegion(fbo.getColorBufferTexture());
        //tex.flip(false, true);
        //Texture t = tex.getTexture();
        Texture t = fbo.getColorBufferTexture();
        Texture t2 = new Texture(0, 0, Pixmap.Format.RGB888);
        if (!t.getTextureData().isPrepared())
        	t.getTextureData().prepare();
        Pixmap p = t.getTextureData().consumePixmap();
        return p;
    }
    
    public Pixmap renderText(Color fg_color, Color bg_color, BitmapFont font, String text){
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        SpriteBatch spriteBatch = new SpriteBatch();

        FrameBuffer m_fbo = new FrameBuffer(Format.RGB565, 252, 24, false);
        m_fbo.begin();
        Gdx.gl.glClearColor(bg_color.r, bg_color.g, bg_color.b, bg_color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
        spriteBatch.setProjectionMatrix(normalProjection);

        spriteBatch.begin();
        spriteBatch.setColor(fg_color);
        //do some drawing ***here's where you draw your dynamic texture***
        font.draw(spriteBatch, text,  0, 0);
        spriteBatch.end();//finish write to buffer

        Pixmap pm = ScreenUtils.getFrameBufferPixmap(0, 0, (int) width, (int) height);//write frame buffer to Pixmap

        m_fbo.end();
        m_fbo.dispose();
        m_fbo = null;
        spriteBatch.dispose();
        return pm;
    }
}
