package mc.alk.paintball;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import mc.alk.arena.BattleArena;
import mc.alk.arena.controllers.APIRegistrationController;
import mc.alk.arena.util.Log;
public class Paintball extends JavaPlugin{

	@Override
	public void onEnable(){
		/// Registers this plugin with BattleArena
		/// this: our plugin
		/// "Paintball": The name of our competition
		/// "pb": the name of our command alias ( who really wants to type in the entire word paintball?)
		/// PaintballArena.class: which arena should this competition use
		/// Register a Paintball
		APIRegistrationController.registerCompetition(this, "Paintball", "pb", BattleArena.createArenaFactory( PaintballArena.class) );

		/// create our default config if it doesn't exist
		saveDefaultConfig();

		/// Load our config options
		loadConfig();

		Log.info("[" + getName()+ "] v" + getDescription().getVersion()+ " enabled!");
	}

	@Override
	public void onDisable(){
		Log.info("[" + getName()+ "] v" + getDescription().getVersion()+ " stopping!");
	}

	@Override
	public void reloadConfig(){
		super.reloadConfig();
		loadConfig();
	}

	public void loadConfig(){
		/// Allow the damage to be set through the config.yml, if it exists and has the section: 'damage: <value>'
		/// Like 'damage: 15'
		FileConfiguration config = getConfig();
		PaintballArena.damage = config.getInt("damage", 20);
	}

}
