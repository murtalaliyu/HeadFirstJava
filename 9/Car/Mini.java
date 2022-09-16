import java.awt.Color;

public class Mini extends Car {
  private Color color;

  public Mini() {
    this(Color.RED);
  }

  public Mini(Color c) {
    super("Mini");
    color = c;
  }

  public Mini(int size) {
    this(Color.RED);      // illegal
    // super(size);       // won't work. only 1 allowed
  }
}
