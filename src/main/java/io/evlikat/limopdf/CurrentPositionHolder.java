package io.evlikat.limopdf;

import io.evlikat.limopdf.util.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrentPositionHolder implements CurrentPosition {

    private Position position;
    private float lastBottomMargin;
    private boolean blank;

    public CurrentPositionHolder() {
        this(null, 0f, true);
    }

    public void reset(Position newPosition) {
        this.position = newPosition;
        this.lastBottomMargin = 0f;
        this.blank = true;
    }

    @Override
    public float getX() {
        return position.getX();
    }

    @Override
    public float getY() {
        return position.getY();
    }

    public void minusY(float dy) {
        position = position.minusY(dy);
    }
}
