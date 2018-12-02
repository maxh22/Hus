package Hus;

import Hus.PlayingField;
import org.junit.Assert;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayingFieldTest {

    @Test
    public void init_whenPlayingFieldInitialised_everyHoleHasTwoStonesBut8FieldsAreEmpty() {
        int result = 0;
        int expected = 48;

        PlayingField playingField = new PlayingField();
        //default constructor is calling the init-method

        for (int i = 0; i < 32; i++) {
            result += playingField.getNumberOfStones(i);
        }

        assertThat(result).isEqualTo(expected);
    }
}
