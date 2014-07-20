/**
 * Created by tsangwailam on 19/7/14.
 */
package digicrafts.extensions.data {
public class Leaderboard {

    public var displayText:String;
    public var id:String;
    public var imageURL:String;
    public var name:String;
    public var scoreFormat:String;//ScoreFormat


    public function Leaderboard(obj:*) {
        // Fill in the properties
        for(var key:String in obj){
            if(this.hasOwnProperty(key))
                this[key]=obj[key];
        }
    }
}
}
