package phoenixfrenzy.redcastlemedia.bukkit.hireme;

import phoenixfrenzy.redcastlemedia.bukkit.hireme.jobs.JobCategory;
import phoenixfrenzy.redcastlemedia.bukkit.hireme.listeners.EntityListener;
import phoenixfrenzy.redcastlemedia.bukkit.hireme.listeners.PluginServerListener;
import phoenixfrenzy.redcastlemedia.bukkit.hireme.listeners.CustomListener;
import phoenixfrenzy.redcastlemedia.bukkit.hireme.listeners.BlockListener;
import java.util.Map;
import java.util.logging.Logger;
import multitallented.redcastlemedia.bukkit.townships.Townships;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import phoenixfrenzy.redcastlemedia.bukkit.hireme.jobs.Job;
import phoenixfrenzy.redcastlemedia.bukkit.hireme.jobs.JobManager;

/**
 *
 * @author Phoenix_Frenzy
 */
public class HireMe extends JavaPlugin 
{
    private Logger log;
    private FileConfiguration config;
    public static ConfigManager configManager;
    public static Economy econ;
    public static Permission perms;
    private PluginServerListener serverListener;
    private BlockListener blockListener;
    private EntityListener entityListener;
    public static Townships townships;
    private JobManager jobManager;
    
    @Override
    public void onDisable() 
    {
        log = Logger.getLogger("Minecraft");
        log.info("[HireMe] is now disabled!");
    }
    
    @Override
    public void onEnable()
    {
        log = Logger.getLogger("Minecraft");
        log.info("[HireMe] is now enabled!"); 
        
        //setup configs
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
        configManager = new ConfigManager(config, this);
        
        jobManager = new JobManager(this);
                
        setupPermissions();
        setupEconomy();
        
        
        //Register Listeners Here
        serverListener = new PluginServerListener(this);
        blockListener = new BlockListener(this);
        entityListener = new EntityListener(this);
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(blockListener, this);
        
        pm.registerEvents(serverListener, this);
        
        pm.registerEvents(entityListener, this);   
        
        pm.registerEvents(new CustomListener(this), this);
        
        //pm.registerEvents(new GUIListener(), this);
        
        //pm.registerEvents(new InfoGUIListener(regionManager), this);
        
        
        //Check for Dependencies
        //Look for Townships
        log.info("[HireMe] is looking for Townships...");
        Plugin currentPlugin = pm.getPlugin("Townships");
        if (currentPlugin != null) 
        {
            log.info("[HireMe] found Townships!");
            townships = ((Townships) currentPlugin);
        } 
        else 
        {
            log.info("[HireMe] DID NOT find Townships, waiting for Townships to be enabled.");
        }
    }
    public static ConfigManager getConfigManager() 
    {
        return configManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) 
    {
        if (args.length > 0 && args[0].equals("create") && !(sender instanceof Player))
        {
            Player player = (Player) sender;
            int id = 0;
            String title = "";
            String description = "";
            double pay = 0;
            String employer = "";
            JobCategory category = new JobCategory();
            String target = "";
            Location location = player.getLocation();
            
            Job newJob = new Job(id, title, description, pay, employer, category, target, location);
        }
        else if(args.length > 0 && args[0].equals("list"))
        {
            //List Jobs
            
        }
        return true;
    }

    public boolean setupEconomy() 
    {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) 
        {
            econ = rsp.getProvider();
            if (econ != null)
                System.out.println("[HireMe] Hooked into " + econ.getName());
        }
        return econ != null;
    }  
        
    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) 
        {
            perms = permissionProvider.getProvider();
            if (perms != null)
                System.out.println("[HeroStronghold] Hooked into " + perms.getName());
        }
        return (perms != null);
    }
    
    public void setConfigManager(ConfigManager cm) 
    {
        configManager = cm;
    }   
    public JobManager getJobManager() 
    {
        return jobManager;
    }      
}