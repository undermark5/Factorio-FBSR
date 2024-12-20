package com.demod.fbsr.entity;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.demod.factorio.DataTable;
import com.demod.factorio.prototype.EntityPrototype;
import com.demod.fbsr.BlueprintEntity;
import com.demod.fbsr.EntityRendererFactory;
import com.demod.fbsr.RenderUtils;
import com.demod.fbsr.Renderer;
import com.demod.fbsr.Sprite;
import com.demod.fbsr.SpriteDef;
import com.demod.fbsr.WorldMap;

public class StorageTankRendering extends EntityRendererFactory {

	public static final int[][][] storageTankPipes = //
			new int[/* NESW */][/* Points */][/* XY */] { //
					{ { 1, 1 }, { -1, -1 } }, // North
					{ { 1, -1 }, { -1, 1 } }, // East
					{ { 1, 1 }, { -1, -1 } }, // South
					{ { 1, -1 }, { -1, 1 } },// West
			};
	private List<SpriteDef> protoSprites;

	@Override
	public void createRenderers(Consumer<Renderer> register, WorldMap map, DataTable dataTable,
			BlueprintEntity entity) {

		List<Sprite> sprites = protoSprites.stream().map(SpriteDef::createSprite).collect(Collectors.toList());
		sprites.forEach(s -> s.source.x = s.source.width * (entity.getDirection().cardinal() % 2));
		register.accept(RenderUtils.spriteRenderer(sprites, entity, protoSelectionBox));
	}

	@Override
	public void initFromPrototype(DataTable dataTable, EntityPrototype prototype) {
		super.initFromPrototype(dataTable, prototype);

		protoSprites = RenderUtils.getSpritesFromAnimation(prototype.lua().get("pictures").get("picture"));
	}

	@Override
	public void populateWorldMap(WorldMap map, DataTable dataTable, BlueprintEntity entity) {
		// FIXME maybe should use the fluid box

		Point2D.Double position = entity.getPosition();

		int[][] pipePoints = storageTankPipes[entity.getDirection().cardinal()];

		for (int[] point : pipePoints) {
			map.setPipe(new Point2D.Double(position.x + point[0], position.y + point[1]));
		}
	}
}
