package scenes;

import ecs.*;
import graphics.Camera;
import graphics.Color;
import graphics.Spritesheet;
import graphics.Window;
import input.Mouse;
import tiles.TMXParser;
import tiles.Tilesystem;
import physics.Transform;
import util.Assets;
import util.Engine;
import util.Scene;
import util.Utils;

import static graphics.Graphics.setDefaultBackground;

public class Demo extends Scene {
    public static void main (String[] args) {
        Engine.init(1920, 1080, "Azurite Engine Demo 1", 0.1f);
    }

    Spritesheet a;
    Spritesheet b;
    Tilesystem t;
    GameObject player;

    public void awake() {
        setDefaultBackground(Color.BLACK);
        camera = new Camera();
        a = new Spritesheet(Assets.getTexture("src/assets/images/tileset.png"), 16, 16, 256, 0, 16);
        b = new Spritesheet(Assets.getTexture("src/assets/images/walls.png"), 16, 16, 256, 0, 16);
        t = new Tilesystem(a, b, 31, 15, 200, 200);

        player = new GameObject("Player", new Transform(600, 600, 100, 100), 2);
        player.addComponent(new PointLight(new Color(250, 255, 181), 30));
        player.addComponent(new SpriteRenderer(a.getSprite(132)));
        player.addComponent(new CharacterController());
    }

    public void update() {
        player.getComponent(PointLight.class).intensity = Utils.map((float)Math.sin(Engine.millis()/600), -1, 1, 100, 140);

        camera.smoothFollow(player.getTransform());
    }
}