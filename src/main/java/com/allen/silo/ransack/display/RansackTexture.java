package com.allen.silo.ransack.display;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.core.geom.Rectangle;
import org.mini2Dx.core.geom.Shape;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.NinePatch;
import org.mini2Dx.core.graphics.ParticleEffect;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.TextureRegion;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RansackTexture extends Texture implements Graphics{
	public static Logger logger = Logger.getLogger(RansackTexture.class.getName());
	
	public RansackTexture(int width, int height, Format format) {
		super(width, height, format);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void preRender(int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stage createStage(Viewport paramViewport) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void drawLineSegment(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawCircle(float paramFloat1, float paramFloat2, int paramInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawCircle(float paramFloat1, float paramFloat2, float paramFloat3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillCircle(float paramFloat1, float paramFloat2, int paramInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillCircle(float paramFloat1, float paramFloat2, float paramFloat3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawTriangle(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4,
			float paramFloat5, float paramFloat6) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillTriangle(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4,
			float paramFloat5, float paramFloat6) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawPolygon(float[] paramArrayOfFloat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillPolygon(float[] paramArrayOfFloat, short[] paramArrayOfShort) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawString(String paramString, float paramFloat1, float paramFloat2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawString(String paramString, float paramFloat1, float paramFloat2, float paramFloat3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawString(String paramString, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawTexture(Texture paramTexture, float paramFloat1, float paramFloat2) {
		logger.log(Level.INFO, "1");
		
	}

	@Override
	public void drawTexture(Texture paramTexture, float paramFloat1, float paramFloat2, boolean paramBoolean) {
		logger.log(Level.INFO, "2");
		
	}

	@Override
	public void drawTexture(Texture paramTexture, float paramFloat1, float paramFloat2, float paramFloat3,
			float paramFloat4) {
		logger.log(Level.INFO, "3");
		
	}

	@Override
	public void drawTexture(Texture paramTexture, float paramFloat1, float paramFloat2, float paramFloat3,
			float paramFloat4, boolean paramBoolean) {
		logger.log(Level.INFO, "4");
		
	}

	@Override
	public void drawTextureRegion(TextureRegion paramTextureRegion, float paramFloat1, float paramFloat2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawTextureRegion(TextureRegion paramTextureRegion, float paramFloat1, float paramFloat2,
			float paramFloat3, float paramFloat4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawTextureRegion(TextureRegion paramTextureRegion, float paramFloat1, float paramFloat2,
			float paramFloat3, float paramFloat4, float paramFloat5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawShape(Shape paramShape) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillShape(Shape paramShape) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawSprite(Sprite paramSprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawSprite(Sprite paramSprite, float paramFloat1, float paramFloat2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawSpriteCache(SpriteCache paramSpriteCache, int paramInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawStage(Stage paramStage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawParticleEffect(ParticleEffect paramParticleEffect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawNinePatch(NinePatch paramNinePatch, float paramFloat1, float paramFloat2, float paramFloat3,
			float paramFloat4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawNinePatch(NinePatchDrawable paramNinePatchDrawable, float paramFloat1, float paramFloat2,
			float paramFloat3, float paramFloat4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawTiledDrawable(TiledDrawable paramTiledDrawable, float paramFloat1, float paramFloat2,
			float paramFloat3, float paramFloat4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawBitmapFontCache(BitmapFontCache paramBitmapFontCache) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate(float paramFloat1, float paramFloat2, float paramFloat3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRotation(float paramFloat1, float paramFloat2, float paramFloat3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scale(float paramFloat1, float paramFloat2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setScale(float paramFloat1, float paramFloat2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearScaling() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void translate(float paramFloat1, float paramFloat2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTranslation(float paramFloat1, float paramFloat2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClip(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClip(Rectangle paramRectangle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle removeClip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle peekClip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void peekClip(Rectangle paramRectangle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTint(Color paramColor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFont(BitmapFont paramBitmapFont) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableBlending() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableBlending() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShaderProgram(ShaderProgram paramShaderProgram) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ShaderProgram getShaderProgram() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearShaderProgram() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBlendFunction(int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearBlendFunction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLineHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLineHeight(int paramInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setColor(Color paramColor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getBackgroundColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBackgroundColor(Color paramColor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BitmapFont getFont() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getTint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getScaleX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getScaleY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTranslationX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTranslationY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRotationX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRotationY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Matrix4 getProjectionMatrix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWindowReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getWindowWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWindowHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getViewportWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getViewportHeight() {
		// TODO Auto-generated method stub
		return 0;
	}


}
