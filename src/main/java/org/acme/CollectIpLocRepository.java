/* TODO: en-tête à modifier
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.acme;

import org.locationtech.jts.geom.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * A repository for CollectIpLoc data retrieved.
 */
public final class CollectIpLocRepository {

    private static final String DROP_TABLE = """
      DROP TABLE IF EXISTS collectiploc""";

    // TODO:  - champ : created at
    //        - adresse MAC
    //        - cookie avec identifiant de tracking (même user fait les mêmes opérations plusieurs fois)
    //        - champ : headers (tous dans une colonne)
    //        - (pages session scoped OU cookie avec UUID)
    //        - champ : mode d'acquisition, set à WEB pour le moment (pas d'app mobile pour le moment)
    private static final String CREATE_TABLE = """
      CREATE TABLE IF NOT EXISTS collectiploc (
          id integer PRIMARY KEY,
          longitude real,
          latitude real,
          altitude real,
          accuracy real,
          altitudeAccuracy real,
          heading real,
          speed real,
          ip text
      )""";

    private static final String INSERT_SQL =
            """
                INSERT INTO collectiploc(longitude, latitude, altitude, accuracy, altitudeAccuracy, heading, speed, ip)
                VALUES(?,?,?,?,?,?,?,?)""";

    private static final Logger logger = LoggerFactory.getLogger(CollectIpLocRepository.class);

    private final DataSource dataSource;

    /**
     * Constructs an {@code CollectIpLocRepository} with the specified {@code DataSource}.
     *
     * @param dataSource the data source
     */
    public CollectIpLocRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Drops the table.
     */
    public void dropTable() {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(DROP_TABLE)) {
            statement.execute();
        } catch (SQLException e) {
            logger.error("Unable to drop collectiploc table", e);
        }
    }

    /**
     * Creates the table.
     */
    public void createTable() {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(CREATE_TABLE)) {
            statement.execute();
        } catch (SQLException e) {
            logger.error("Unable to create collectiploc table", e);
        }
    }

    /**
     * Saves the coordinates and the ip address in the repository.
     *
     * @param coordinate the coordinates (longitude, latitude)
     * @param ip the ip address
     */
    public void save(Coordinate coordinate, double accuracy, double altitudeAccuracy, double heading, double speed, String ip) {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setDouble(1, coordinate.getX());
            statement.setDouble(2, coordinate.getY());
            statement.setDouble(3, coordinate.getZ());
            statement.setDouble(4, accuracy);
            statement.setDouble(5, altitudeAccuracy);
            statement.setDouble(6, heading);
            statement.setDouble(7, speed);
            statement.setString(8, ip);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to save data", e);
        }
    }
}
