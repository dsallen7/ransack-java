package com.allen.silo.ransack.display;

import org.mini2Dx.core.assets.FallbackFileHandleResolver;
import org.mini2Dx.core.di.annotation.Singleton;
import org.mini2Dx.ui.UiThemeLoader;
import org.mini2Dx.ui.style.UiTheme;

import com.allen.silo.ransack.utils.resolvers.TestExternalFileHandleResolver;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.PixmapLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

@Singleton
public class RansackAssetManager extends AssetManager {
	
	private static FallbackFileHandleResolver fileHandleResolver;
	
	static{
		fileHandleResolver = new FallbackFileHandleResolver(new TestExternalFileHandleResolver(), new InternalFileHandleResolver(), new ExternalFileHandleResolver());
	}
	
	public RansackAssetManager(){
		super(fileHandleResolver);
	    setLoader(UiTheme.class, new UiThemeLoader(this.getFileHandleResolver()));
	    /*
	    setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(this.getFileHandleResolver()));
	    setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(this.getFileHandleResolver()));
	    logFont.fontFileName = "steelfis.ttf";
	    logFont.fontParameters.size = 24;
	    load("FONTS/steelfis.ttf", BitmapFont.class, logFont);
	    */
	    
	    this.loadFont("steelfis.ttf", 24);
	    this.loadFont("courier.ttf", 18);
	    
	    setLoader(Pixmap.class, new PixmapLoader(this.getFileHandleResolver()));
	    setLoader(Texture.class, new TextureLoader(this.getFileHandleResolver()));

		load("IMG/MENU/interface.png", Texture.class);
	    load("ransack-mdx-theme.json", UiTheme.class);
	}
	
	public void loadFont(String filename, int size){
	    FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(fileHandleResolver.resolve("FONTS/"+filename));
	    FreeTypeFontParameter fontParameter = new FreeTypeFontParameter();
	    fontParameter.flip = true;
	    fontParameter.size = size;
	    FreeTypeBitmapFontData logFontData = fontGenerator.generateData(fontParameter);
	    BitmapFont newFont = fontGenerator.generateFont(fontParameter);
	    this.addAsset(filename, BitmapFont.class, newFont);
	}
}
