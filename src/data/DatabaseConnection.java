package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    // File DB SQLite đặt cạnh .jar (hoặc IDE run directory)
    private static final String URL = "jdbc:sqlite:gamedata.db";

    public Connection getConnection() {
        Connection connection = null;
        try {
            // Đảm bảo driver đã nạp
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection(URL);

            // Thiết lập khuyến nghị cho game offline
            try (Statement st = connection.createStatement()) {
                // Bật khóa ngoại
                st.execute("PRAGMA foreign_keys = ON;");
                // Chế độ WAL giúp an toàn + hiệu năng ổn định
                st.execute("PRAGMA journal_mode = WAL;");
                // Mức đồng bộ hợp lý cho desktop (an toàn hơn OFF)
                st.execute("PRAGMA synchronous = NORMAL;");
            }
        } catch (Exception e) {
            System.err.println("SQLite connect failed!");
            e.printStackTrace();
        }
        return connection;
    }

    /** Gọi 1 lần khi khởi động game để tạo schema nếu chưa có */
    public void setupDatabase() {
        String[] createTableSqls = {
            // account
            "CREATE TABLE IF NOT EXISTS account (\n" +
            "  idaccount   INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  username    TEXT UNIQUE NOT NULL,\n" +
            "  password    TEXT NOT NULL\n" +
            ");",

            // player
            "CREATE TABLE IF NOT EXISTS player (\n" +
            "  id              INTEGER PRIMARY KEY,\n" + // id = idaccount (không auto)
            "  level           INTEGER,\n" +
            "  max_life        INTEGER,\n" +
            "  life            INTEGER,\n" +
            "  max_mana        INTEGER,\n" +
            "  mana            INTEGER,\n" +
            "  strength        INTEGER,\n" +
            "  exp             INTEGER,\n" +
            "  next_level_exp  INTEGER,\n" +
            "  coin            INTEGER,\n" +
            "  currentArmor    INTEGER,\n" +
            "  currentWeapon   INTEGER,\n" +
            "  currentBook     INTEGER,\n" +
            "  map_id          INTEGER,\n" +
            "  currentArea     INTEGER,\n" +
            "  worldX          INTEGER,\n" +
            "  worldY          INTEGER,\n" +
            "  skill1          INTEGER,\n" +
            "  skill2          INTEGER,\n" +
            "  skill3          INTEGER,\n" +
            "  attack          INTEGER,\n" +
            "  defense         INTEGER,\n" +
            "  FOREIGN KEY(id) REFERENCES account(idaccount)\n" +
            ");",

            // gamepanel
            "CREATE TABLE IF NOT EXISTS gamepanel (\n" +
            "  id_panel                INTEGER PRIMARY KEY,\n" +
            "  dragonBattleOn          INTEGER,\n" +
            "  ishigamiBattleOn        INTEGER,\n" +
            "  endSummon               INTEGER,\n" +
            "  endThunderSummon        INTEGER,\n" +
            "  afterSummon             INTEGER,\n" +
            "  endDialogue1            INTEGER,\n" +
            "  endDialogue2            INTEGER,\n" +
            "  endDialogue3            INTEGER,\n" +
            "  orcDefeated             INTEGER,\n" +
            "  zombieWinterDefeated    INTEGER,\n" +
            "  dragonDefeated          INTEGER,\n" +
            "  ishigamiDefeated        INTEGER,\n" +
            "  FOREIGN KEY(id_panel) REFERENCES player(id)\n" +
            ");",

            // ui
            "CREATE TABLE IF NOT EXISTS ui (\n" +
            "  ui_id               INTEGER PRIMARY KEY,\n" +
            "  counterSummon       INTEGER,\n" +
            "  soundSummon         INTEGER,\n" +
            "  soundThunder        INTEGER,\n" +
            "  endLine             INTEGER,\n" +
            "  canTouchEvent       INTEGER,\n" +
            "  trapRock            INTEGER,\n" +
            "  trapSpikeArrow      INTEGER,\n" +
            "  endDialogueMap1     INTEGER,\n" +
            "  endDialogueMap2     INTEGER,\n" +
            "  endDialogueMap3     INTEGER,\n" +
            "  endDialogueMap35    INTEGER,\n" +
            "  endDialogueMap4     INTEGER,\n" +
            "  endDialogueMap45    INTEGER,\n" +
            "  endGriffon          INTEGER,\n" +
            "  FOREIGN KEY(ui_id) REFERENCES player(id)\n" +
            ");",

            // inventory
            "CREATE TABLE IF NOT EXISTS inventory (\n" +
            "  player_id       INTEGER NOT NULL,\n" +
            "  item_name       TEXT NOT NULL,\n" +
            "  item_amount     INTEGER NOT NULL,\n" +
            "  FOREIGN KEY(player_id) REFERENCES player(id)\n" +
            ");",

            // map_objects
            "CREATE TABLE IF NOT EXISTS map_objects (\n" +
            "  object_id   INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  map_id      INTEGER,\n" +
            "  object_name TEXT,\n" +
            "  world_x     INTEGER,\n" +
            "  world_y     INTEGER,\n" +
            "  loot_name   TEXT,\n" +
            "  opened      INTEGER,\n" +
            "  player_id   INTEGER,\n" +
            "  FOREIGN KEY(player_id) REFERENCES player(id)\n" +
            ");",

            // monster
            "CREATE TABLE IF NOT EXISTS monster (\n" +
            "  mon_id      INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  map_id      INTEGER,\n" +
            "  mon_name    TEXT,\n" +
            "  world_x     INTEGER,\n" +
            "  world_y     INTEGER,\n" +
            "  life        INTEGER,\n" +
            "  alive       INTEGER,\n" +
            "  player_id   INTEGER,\n" +
            "  inRage      INTEGER,\n" +
            "  sleep       INTEGER,\n" +
            "  endPhase1   INTEGER,\n" +
            "  startPhase2 INTEGER,\n" +
            "  beingPhase  INTEGER,\n" +
            "  attack      INTEGER,\n" +
            "  defense     INTEGER,\n" +
            "  speed       INTEGER,\n" +
            "  FOREIGN KEY(player_id) REFERENCES player(id)\n" +
            ");",

            // (tùy chọn) index hỗ trợ đọc nhanh
            "CREATE INDEX IF NOT EXISTS idx_inventory_player ON inventory(player_id);",
            "CREATE INDEX IF NOT EXISTS idx_map_objects_player ON map_objects(player_id, map_id);",
            "CREATE INDEX IF NOT EXISTS idx_monster_player ON monster(player_id, map_id);"
        };

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement()) {
            if (conn == null) return;

            // Bật khóa ngoại trước khi tạo bảng
            stmt.execute("PRAGMA foreign_keys = ON;");

            System.out.println("Setting up database schema...");
            for (String sql : createTableSqls) {
                stmt.execute(sql);
            }
            System.out.println("Database setup complete.");
        } catch (SQLException e) {
            System.err.println("Error setting up database tables:");
            e.printStackTrace();
        }
    }
}
