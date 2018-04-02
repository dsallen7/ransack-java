package com.allen.silo.ransack.utils.resolvers;

import com.allen.silo.ransack.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

public class TestExternalFileHandleResolver extends ExternalFileHandleResolver {
	@Override
	public FileHandle resolve (String fileName) {
		return Gdx.files.external(Constants.EXTERNAL_ASSETS_PATH+fileName);
	}
}
