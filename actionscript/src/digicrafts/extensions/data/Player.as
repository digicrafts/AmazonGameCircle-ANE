/**
 * Created by tsangwailam on 17/7/14.
 */
package digicrafts.extensions.data {
public class Player {

    public var alias:String;
    public var avatarUrl:String;
    public var playerId:String;

    public function Player(obj:*) {

        if(obj) {
            // Fill in the properties
            for (var key:String in obj) {
                if (this.hasOwnProperty(key))
                    this[key] = obj[key];
            }
        }
    }
}
}
