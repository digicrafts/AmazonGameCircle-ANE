/**
 * Created by tsangwailam on 20/7/14.
 */
package digicrafts.extensions.data {
public class LeaderboardPercentileItem {

    public var percentile:String;
    public var player:Player;
    public var playerScore:int;

    public function LeaderboardPercentileItem(obj:*) {

        // Fill in the properties
        for(var key:String in obj){
            if(this.hasOwnProperty(key)){
                if(key=='player') {
                    player=new Player(obj[key]);
                } else {
                    this[key]=obj[key];
                }
            }

        }

    }
}
}
