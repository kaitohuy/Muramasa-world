package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Entity;
import main.GamePanel;

public class SaveLoad {
    GamePanel gp;
    DatabaseConnection dbManager;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
        this.dbManager = new DatabaseConnection();
    }

    public void save() {
        Connection conn = null;
        try {
            conn = dbManager.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            // === PLAYER ===
            boolean exists = checkPlayerExists(gp.player.playerId);
            if (!exists) {
                String playerInsertSql =
                        "INSERT INTO player (id, level, max_life, life, max_mana, mana, strength, exp, next_level_exp, coin, " +
                        "currentArmor, currentWeapon, currentBook, map_id, currentArea, worldX, worldY, " +
                        "skill1, skill2, skill3, attack, defense) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(playerInsertSql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setInt(1, gp.player.playerId);
                    ps.setInt(2, gp.player.level);
                    ps.setInt(3, gp.player.maxLife);
                    ps.setInt(4, gp.player.life);
                    ps.setInt(5, gp.player.maxMana);
                    ps.setInt(6, gp.player.mana);
                    ps.setInt(7, gp.player.strength);
                    ps.setInt(8, gp.player.exp);
                    ps.setInt(9, gp.player.nextLevelExp);
                    ps.setInt(10, gp.player.coin);
                    ps.setInt(11, gp.player.getCurrentArmorSlot());
                    ps.setInt(12, gp.player.getCurrentWeaponSlot());
                    ps.setInt(13, gp.player.getCurrentBookSlot());
                    ps.setInt(14, gp.currentMap);
                    ps.setInt(15, gp.currentArea);
                    ps.setInt(16, gp.player.worldX);
                    ps.setInt(17, gp.player.worldY);
                    ps.setBoolean(18, gp.player.canUseSkill1);
                    ps.setBoolean(19, gp.player.canUseSkill2);
                    ps.setBoolean(20, gp.player.canUseSkill3);
                    ps.setInt(21, gp.player.attack);
                    ps.setInt(22, gp.player.defense);
                    ps.executeUpdate();
                }
            } else {
                String playerUpdateSql =
                        "UPDATE player SET level = ?, max_life = ?, life = ?, max_mana = ?, mana = ?, strength = ?, " +
                        "exp = ?, next_level_exp = ?, coin = ?, currentArmor = ?, currentWeapon = ?, currentBook = ?, " +
                        "map_id = ?, currentArea = ?, worldX = ?, worldY = ?, skill1 = ?, skill2 = ?, skill3 = ?, " +
                        "attack = ?, defense = ? WHERE id = ?";
                try (PreparedStatement ps = conn.prepareStatement(playerUpdateSql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setInt(1, gp.player.level);
                    ps.setInt(2, gp.player.maxLife);
                    ps.setInt(3, gp.player.life);
                    ps.setInt(4, gp.player.maxMana);
                    ps.setInt(5, gp.player.mana);
                    ps.setInt(6, gp.player.strength);
                    ps.setInt(7, gp.player.exp);
                    ps.setInt(8, gp.player.nextLevelExp);
                    ps.setInt(9, gp.player.coin);
                    ps.setInt(10, gp.player.getCurrentArmorSlot());
                    ps.setInt(11, gp.player.getCurrentWeaponSlot());
                    ps.setInt(12, gp.player.getCurrentBookSlot());
                    ps.setInt(13, gp.currentMap);
                    ps.setInt(14, gp.currentArea);
                    ps.setInt(15, gp.player.worldX);
                    ps.setInt(16, gp.player.worldY);
                    ps.setBoolean(17, gp.player.canUseSkill1);
                    ps.setBoolean(18, gp.player.canUseSkill2);
                    ps.setBoolean(19, gp.player.canUseSkill3);
                    ps.setInt(20, gp.player.attack);
                    ps.setInt(21, gp.player.defense);
                    ps.setInt(22, gp.player.playerId);
                    ps.executeUpdate();
                }
            }

            // === GAMEPANEL ===
            try (PreparedStatement del = conn.prepareStatement("DELETE FROM gamepanel WHERE id_panel = ?")) {
                del.setInt(1, gp.player.playerId);
                del.executeUpdate();
            }
            String panelSql = "INSERT INTO gamepanel " +
                    "(id_panel, dragonBattleOn, ishigamiBattleOn, endSummon, endThunderSummon, afterSummon, " +
                    " endDialogue1, endDialogue2, endDialogue3, orcDefeated, zombieWinterDefeated, " +
                    " dragonDefeated, ishigamiDefeated) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(panelSql)) {
                ps.setInt(1, gp.player.playerId);
                ps.setBoolean(2, gp.dragonBattleOn);
                ps.setBoolean(3, gp.ishigamiBattleOn);
                ps.setBoolean(4, gp.endSummon);
                ps.setBoolean(5, gp.endThunderSummon);
                ps.setBoolean(6, gp.afterSummon);
                ps.setBoolean(7, gp.endDialogue1);
                ps.setBoolean(8, gp.endDialogue2);
                ps.setBoolean(9, gp.endDialogue3);
                ps.setBoolean(10, Progress.orcDefeated);
                ps.setBoolean(11, Progress.zombieWinterDefeated);
                ps.setBoolean(12, Progress.dragonDefeated);
                ps.setBoolean(13, Progress.ishigamiDefeated);
                ps.executeUpdate();
            }

            // === UI ===
            try (PreparedStatement del = conn.prepareStatement("DELETE FROM ui WHERE ui_id = ?")) {
                del.setInt(1, gp.player.playerId);
                del.executeUpdate();
            }
            String uiSql = "INSERT INTO ui " +
                    "(ui_id, counterSummon, soundSummon, soundThunder, endLine, canTouchEvent, trapRock, trapSpikeArrow, " +
                    " endDialogueMap1, endDialogueMap2, endDialogueMap3, endDialogueMap35, endDialogueMap4, endDialogueMap45, endGriffon) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(uiSql)) {
                ps.setInt(1, gp.player.playerId);
                ps.setInt(2, gp.ui.counterSummon);
                ps.setBoolean(3, gp.ui.soundSummon);
                ps.setBoolean(4, gp.ui.soundThunder);
                ps.setBoolean(5, gp.ui.endLine);
                ps.setBoolean(6, gp.eHandler.canTouchEvent);
                ps.setBoolean(7, gp.eHandler.trapRock);
                ps.setBoolean(8, gp.eHandler.trapSpikeArrow);
                ps.setBoolean(9, gp.eHandler.endDialogueMap1);
                ps.setBoolean(10, gp.eHandler.endDialogueMap2);
                ps.setBoolean(11, gp.eHandler.endDialogueMap3);
                ps.setBoolean(12, gp.eHandler.endDialogueMap35);
                ps.setBoolean(13, gp.eHandler.endDialogueMap4);
                ps.setBoolean(14, gp.eHandler.endDialogueMap45);
                ps.setBoolean(15, gp.eHandler.endGriffon);
                ps.executeUpdate();
            }

            // === INVENTORY ===
            try (PreparedStatement del = conn.prepareStatement("DELETE FROM inventory WHERE player_id = ?")) {
                del.setInt(1, gp.player.playerId);
                del.executeUpdate();
            }
            String inventorySql = "INSERT INTO inventory (player_id, item_name, item_amount) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(inventorySql)) {
                for (var item : gp.player.inventory) {
                    ps.setInt(1, gp.player.playerId);
                    ps.setString(2, item.name);
                    ps.setInt(3, item.amount);
                    ps.executeUpdate();
                }
            }

            // === MAP_OBJECTS ===
            try (PreparedStatement del = conn.prepareStatement("DELETE FROM map_objects WHERE player_id = ?")) {
                del.setInt(1, gp.player.playerId);
                del.executeUpdate();
            }
            // reset AUTOINCREMENT cho map_objects (SQLite)
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("DELETE FROM sqlite_sequence WHERE name = 'map_objects'");
            }
            String mapObjectSql = "INSERT INTO map_objects " +
                    "(map_id, object_name, world_x, world_y, loot_name, opened, player_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(mapObjectSql)) {
                for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                    for (var obj : gp.obj[mapNum]) {
                        if (obj != null) {
                            ps.setInt(1, mapNum);
                            ps.setString(2, obj.name);
                            ps.setInt(3, obj.worldX);
                            ps.setInt(4, obj.worldY);
                            ps.setString(5, (obj.loot != null ? obj.loot.name : null));
                            ps.setBoolean(6, obj.opened);
                            ps.setInt(7, gp.player.playerId);
                            ps.executeUpdate();
                        }
                    }
                }
            }

            // === MONSTER ===
            try (PreparedStatement del = conn.prepareStatement("DELETE FROM monster WHERE player_id = ?")) {
                del.setInt(1, gp.player.playerId);
                del.executeUpdate();
            }
            // reset AUTOINCREMENT cho monster (SQLite)
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("DELETE FROM sqlite_sequence WHERE name = 'monster'");
            }
            String monsterSql = "INSERT INTO monster " +
                    "(map_id, mon_name, world_x, world_y, life, alive, player_id, inRage, sleep, endPhase1, startPhase2, beingPhase, attack, defense, speed) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(monsterSql)) {
                for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                    for (var mon : gp.monster[mapNum]) {
                        if (mon != null) {
                            ps.setInt(1, mapNum);
                            ps.setString(2, mon.name);
                            ps.setInt(3, mon.worldX);
                            ps.setInt(4, mon.worldY);
                            ps.setInt(5, mon.life);
                            ps.setBoolean(6, mon.alive);
                            ps.setInt(7, gp.player.playerId);
                            ps.setBoolean(8, mon.inRage);
                            ps.setBoolean(9, mon.sleep);
                            ps.setBoolean(10, mon.endPhase1);
                            ps.setBoolean(11, mon.startPhase2);
                            ps.setBoolean(12, mon.beingPhase);
                            ps.setInt(13, mon.attack);
                            ps.setInt(14, mon.defense);
                            ps.setInt(15, mon.speed);
                            ps.executeUpdate();
                        }
                    }
                }
            }

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
    }

    public void load() {
        try (Connection conn = dbManager.getConnection()) {
            // Inventory
            String inventorySql = "SELECT item_name, item_amount FROM inventory WHERE player_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(inventorySql)) {
                ps.setInt(1, gp.player.playerId);
                try (ResultSet rs = ps.executeQuery()) {
                    gp.player.inventory.clear();
                    while (rs.next()) {
                        Entity item = gp.eGenerator.getObject(rs.getString("item_name"));
                        if (item != null) {
                            item.amount = rs.getInt("item_amount");
                            gp.player.inventory.add(item);
                        }
                    }
                }
            }

            // Player
            String playerSql = "SELECT * FROM player WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(playerSql)) {
                ps.setInt(1, gp.player.playerId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        gp.player.level = rs.getInt("level");
                        gp.player.maxLife = rs.getInt("max_life");
                        gp.player.life = rs.getInt("life");
                        gp.player.maxMana = rs.getInt("max_mana");
                        gp.player.mana = rs.getInt("mana");
                        gp.player.strength = rs.getInt("strength");
                        gp.player.exp = rs.getInt("exp");
                        gp.player.nextLevelExp = rs.getInt("next_level_exp");
                        gp.player.coin = rs.getInt("coin");
                        int armorSlot = rs.getInt("currentArmor");
                        int swordSlot = rs.getInt("currentWeapon");
                        int bookSlot = rs.getInt("currentBook");
                        gp.currentMap = rs.getInt("map_id");
                        gp.nextArea = rs.getInt("currentArea");
                        gp.player.worldX = rs.getInt("worldX");
                        gp.player.worldY = rs.getInt("worldY");
                        gp.player.canUseSkill1 = rs.getBoolean("skill1");
                        gp.player.canUseSkill2 = rs.getBoolean("skill2");
                        gp.player.canUseSkill3 = rs.getBoolean("skill3");
                        gp.player.attack = rs.getInt("attack");
                        gp.player.defense = rs.getInt("defense");

                        gp.changeTileMap();
                        gp.setWorld();

                        if (armorSlot >= 0 && armorSlot < gp.player.inventory.size()) {
                            gp.player.currentArmor = gp.player.inventory.get(armorSlot);
                        }
                        if (swordSlot >= 0 && swordSlot < gp.player.inventory.size()) {
                            gp.player.currentWeapon = gp.player.inventory.get(swordSlot);
                            gp.player.getAttackImage();
                        }
                        if (bookSlot >= 0 && bookSlot < gp.player.inventory.size()) {
                            gp.player.currentBook = gp.player.inventory.get(bookSlot);
                        }
                    }
                }
            }

            // GamePanel
            String panelSql = "SELECT * FROM gamepanel WHERE id_panel = ?";
            try (PreparedStatement ps = conn.prepareStatement(panelSql)) {
                ps.setInt(1, gp.player.playerId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        gp.dragonBattleOn = rs.getBoolean("dragonBattleOn");
                        gp.ishigamiBattleOn = rs.getBoolean("ishigamiBattleOn");
                        gp.endSummon = rs.getBoolean("endSummon");
                        gp.endThunderSummon = rs.getBoolean("endThunderSummon");
                        gp.afterSummon = rs.getBoolean("afterSummon");
                        gp.endDialogue1 = rs.getBoolean("endDialogue1");
                        gp.endDialogue2 = rs.getBoolean("endDialogue2");
                        gp.endDialogue3 = rs.getBoolean("endDialogue3");
                        Progress.orcDefeated = rs.getBoolean("orcDefeated");
                        Progress.zombieWinterDefeated = rs.getBoolean("zombieWinterDefeated");
                        Progress.dragonDefeated = rs.getBoolean("dragonDefeated");
                        Progress.ishigamiDefeated = rs.getBoolean("ishigamiDefeated");
                    }
                }
            }

            // UI
            String uiSql = "SELECT * FROM ui WHERE ui_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(uiSql)) {
                ps.setInt(1, gp.player.playerId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        gp.ui.counterSummon = rs.getInt("counterSummon");
                        gp.ui.soundSummon = rs.getBoolean("soundSummon");
                        gp.ui.soundThunder = rs.getBoolean("soundThunder");
                        gp.ui.endLine = rs.getBoolean("endLine");
                        gp.eHandler.canTouchEvent = rs.getBoolean("canTouchEvent");
                        gp.eHandler.trapRock = rs.getBoolean("trapRock");
                        gp.eHandler.trapSpikeArrow = rs.getBoolean("trapSpikeArrow");
                        gp.eHandler.endDialogueMap1 = rs.getBoolean("endDialogueMap1");
                        gp.eHandler.endDialogueMap2 = rs.getBoolean("endDialogueMap2");
                        gp.eHandler.endDialogueMap3 = rs.getBoolean("endDialogueMap3");
                        gp.eHandler.endDialogueMap35 = rs.getBoolean("endDialogueMap35");
                        gp.eHandler.endDialogueMap4 = rs.getBoolean("endDialogueMap4");
                        gp.eHandler.endDialogueMap45 = rs.getBoolean("endDialogueMap45");
                        gp.eHandler.endGriffon = rs.getBoolean("endGriffon");
                    }
                }
            }

            // Map objects
            String mapObjectSql = "SELECT map_id, object_id, object_name, world_x, world_y, loot_name, opened " +
                                  "FROM map_objects WHERE player_id = ? ORDER BY object_id";
            try (PreparedStatement ps = conn.prepareStatement(mapObjectSql)) {
                ps.setInt(1, gp.player.playerId);
                try (ResultSet rs = ps.executeQuery()) {

                    int previousMapId = 0;
                    int countObjectMap = 0;
                    int countRow = 0;
                    int numRap = -1;

                    while (rs.next()) {
                        int mapNum = rs.getInt("map_id");

                        if (mapNum != previousMapId) {
                            previousMapId = mapNum;
                            countRow += countObjectMap;
                            countObjectMap = 0;
                            numRap++;
                        } else {
                            countObjectMap++;
                        }

                        int index = rs.getInt("object_id") - 1 - countRow - numRap;

                        if (mapNum < gp.maxMap &&
                            index >= 0 &&
                            index < gp.obj[mapNum].length &&
                            !"NA".equals(rs.getString("object_name"))) {

                            Entity obj = gp.eGenerator.getObject(rs.getString("object_name"));
                            if (obj != null) {
                                obj.worldX = rs.getInt("world_x");
                                obj.worldY = rs.getInt("world_y");
                                if ("chest".equals(obj.name)) {
                                    obj.loot = gp.eGenerator.getObject(rs.getString("loot_name"));
                                }
                                obj.setDialogue();
                                obj.opened = rs.getBoolean("opened");
                                gp.obj[mapNum][index] = obj;
                                if (obj.opened) {
                                    gp.obj[mapNum][index].down1 = gp.obj[mapNum][index].image2;
                                }
                            }
                        }
                    }
                }
            }

            // Monster
            String monsterSql = "SELECT * FROM monster WHERE player_id = ? ORDER BY mon_id";
            try (PreparedStatement ps = conn.prepareStatement(monsterSql)) {
                ps.setInt(1, gp.player.playerId);
                try (ResultSet rs = ps.executeQuery()) {

                    int previousMapId = 0;
                    int countObjectMap = 0;
                    int countRow = 0;
                    int numRap = -1;

                    while (rs.next()) {
                        int mapNum = rs.getInt("map_id");

                        if (mapNum != previousMapId) {
                            previousMapId = mapNum;
                            countRow += countObjectMap;
                            countObjectMap = 0;
                            numRap++;
                        } else {
                            countObjectMap++;
                        }

                        int index = rs.getInt("mon_id") - 1 - countRow - numRap;

                        if (mapNum < gp.maxMap &&
                            index >= 0 &&
                            index < gp.monster[mapNum].length &&
                            !"NA".equals(rs.getString("mon_name"))) {

                            Entity mon = gp.eGenerator.getMonster(rs.getString("mon_name"));
                            if (mon != null) {
                                mon.worldX = rs.getInt("world_x");
                                mon.worldY = rs.getInt("world_y");
                                mon.life = rs.getInt("life");
                                mon.alive = rs.getBoolean("alive");
                                mon.inRage = rs.getBoolean("inRage");
                                mon.sleep = rs.getBoolean("sleep");
                                mon.endPhase1 = rs.getBoolean("endPhase1");
                                mon.startPhase2 = rs.getBoolean("startPhase2");
                                mon.beingPhase = rs.getBoolean("beingPhase");
                                mon.attack = rs.getInt("attack");
                                mon.defense = rs.getInt("defense");
                                mon.speed = rs.getInt("speed");
                                gp.monster[mapNum][index] = mon;
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkExistAccount(String userName) {
        String q = "SELECT COUNT(*) FROM account WHERE username = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPlayerExists(int idaccount) {
        String q = "SELECT 1 FROM player WHERE id = ? LIMIT 1";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, idaccount);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPassword(String userName, String password) {
        if (!checkExistAccount(userName)) return false;
        String q = "SELECT password FROM account WHERE username = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String stored = rs.getString("password");
                    return stored != null && stored.equals(password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addAccount(String userName, String password) {
        String insert = "INSERT INTO account (username, password) VALUES (?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.executeUpdate();

            String qId = "SELECT idaccount FROM account ORDER BY idaccount DESC LIMIT 1";
            try (PreparedStatement q = conn.prepareStatement(qId);
                 ResultSet rs = q.executeQuery()) {
                if (rs.next()) {
                    gp.player.playerId = rs.getInt("idaccount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi thêm tài khoản: " + e.getMessage());
        }
    }

    public void updatePlayerId(String userName, String password) {
        String q = "SELECT idaccount FROM account WHERE username = ? AND password = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, userName);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    gp.player.playerId = rs.getInt("idaccount");
                } else {
                    System.out.println("Không tìm thấy tài khoản với username/password được cung cấp.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi truy vấn id tài khoản: " + e.getMessage());
        }
    }

    public void reset() {
        String deleteGamepanel = "DELETE FROM gamepanel WHERE id_panel = ?";
        String deleteUi = "DELETE FROM ui WHERE ui_id = ?";
        String deleteInventory = "DELETE FROM inventory WHERE player_id = ?";
        String deleteObjects = "DELETE FROM map_objects WHERE player_id = ?";
        String deleteMonsters = "DELETE FROM monster WHERE player_id = ?";
        String deletePlayer = "DELETE FROM player WHERE id = ?";

        try (Connection conn = dbManager.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psGamepanel = conn.prepareStatement(deleteGamepanel);
                 PreparedStatement psUi = conn.prepareStatement(deleteUi);
                 PreparedStatement psInv = conn.prepareStatement(deleteInventory);
                 PreparedStatement psObj = conn.prepareStatement(deleteObjects);
                 PreparedStatement psMon = conn.prepareStatement(deleteMonsters);
                 PreparedStatement psPlayer = conn.prepareStatement(deletePlayer)) {

                psGamepanel.setInt(1, gp.player.playerId);
                psGamepanel.executeUpdate();

                psUi.setInt(1, gp.player.playerId);
                psUi.executeUpdate();

                psInv.setInt(1, gp.player.playerId);
                psInv.executeUpdate();

                psObj.setInt(1, gp.player.playerId);
                psObj.executeUpdate();

                psMon.setInt(1, gp.player.playerId);
                psMon.executeUpdate();

                psPlayer.setInt(1, gp.player.playerId);
                psPlayer.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
