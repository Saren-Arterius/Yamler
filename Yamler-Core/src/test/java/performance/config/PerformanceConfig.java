package performance.config;

import java.util.ArrayList;

import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Comments;
import net.cubespace.Yamler.Config.Config;
import net.cubespace.Yamler.Config.ConfigSection;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class PerformanceConfig extends Config {
    @Comment("Database configuration.")
    private ConfigSection             database;
    @Comments({"The database JDBC address. Should replace dbname with the database name."})
    private final String              database_address        = "jdbc:mysql://localhost/dbname";
    @Comments({"The table to use within given database"})
    private final String              database_table          = "RushPlugin";
    @Comment("Database username, if applicable. Leave empty if unneeded")
    private final String              database_username       = "";
    @Comment("Database password, if applicable. Leave empty if unneeded")
    private final String              database_password       = "";

    @Comment("Gameplay settings")
    private ConfigSection             gameplay;
    @Comment("Delay in minutes during which players cannot break beds.")
    private final int                 gameplay_bedBreakDelay  = 60;
    @Comment("Amount of players each team needs before starting the game.")
    private final int                 gameplay_playersPerTeam = 4;
    @Comments({"Amount of teams in the game.", "Can be 2, 3, 4 or 6"})
    private final int                 gameplay_teams          = 2;
    @Comment("Delay in seconds before starting the game")
    private final int                 gameplay_gameStartDelay = 10;
    @Comment("Delay in seconds for forced respawn after death.")
    private final int                 gameplay_respawnDelay   = 10;

    @Comment("Score system settings")
    private ConfigSection             scoring;
    @Comment("Points earned when winning the game")
    private final int                 scoring_win             = 4;
    @Comment("Points lost when losing the game")
    private final int                 scoring_lose            = 2;
    @Comment("Points earned when killing an enemy")
    private final int                 scoring_kill            = 2;
    @Comment("Points lost when getting killed")
    private final int                 scoring_death           = 1;

    @Comment("Economy settings")
    private ConfigSection             economy;
    @Comment("Money earned when winning the game")
    private final int                 economy_win             = 4;
    @Comment("Money lost when losing the game")
    private final int                 economy_lose            = 2;
    @Comment("Points earned when killing an enemy")
    private final int                 economy_kill            = 2;
    @Comment("Points lost when getting killed")
    private final int                 economy_death           = 1;

    @Comment("Game Setup")
    private ConfigSection             setup;
    @Comment("Game world")
    private final String              setup_world             = "world";
    @Comment("Server to move players to after game over")
    private final String              setup_hubServer         = "hub";
    @Comment("Position of the lobby")
    private final Position            setup_lobbyPosition     = new Position(0, 50, 0);
    @Comment("Position of each spawns")
    private final ArrayList<Position> setup_spawnPosition     = new ArrayList<Position>() {
        {
            for (int i = 0; i < 8; i++) {
                add(new Position(0, 50, 0));
            }
        }
    };
}
