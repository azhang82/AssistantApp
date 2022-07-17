package ktbyte.assistant.app.lyrics;

import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.App;

import java.util.Collections;
import java.util.List;

public class LyricsApp extends App {

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(new GetLyricsAction());}
}
