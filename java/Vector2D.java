class Vector2D implements java.io.Serializable {

  float x, y;

  public Vector2D() {
    this.x = 0;
    this.y = 0;
  }

  public Vector2D(float _x, float _y) {
    this.x = _x;
    this.y = _y;
  }

  public Vector2D(Vector2D v) {
    this.x = v.x;
    this.y = v.y;
  }

  static public Vector2D random() {
    double angle = Math.random() * Math.PI * 2;
    return new Vector2D((float)Math.cos(angle), (float)Math.sin(angle));
  }

  public Vector2D fromAngle(float angle) {
    return Vector2D.fromAngle(this, angle);
  }

  static public Vector2D fromAngle(Vector2D target, float angle) {
    Vector2D result;
    if (target == null) {
      result = new Vector2D((float)Math.cos(angle), (float)Math.sin(angle));
    } else {
      result = new Vector2D((float)Math.cos(angle), (float)Math.sin(angle));
    }
    return result;
  }

  public float mag() {
    return (float) Math.sqrt(this.x*this.x + this.y*this.y);
  }

  public float magSq() {
    return (this.x*this.x + this.y*this.y);
  }

  public Vector2D add(float _x, float _y) {
    return new Vector2D(this.x+_x, this.y+_y);
  }

  public Vector2D add(Vector2D v) {
    return new Vector2D(this.x+v.x, this.y+v.y);
  }

  static public Vector2D add(Vector2D v1, Vector2D v2) {
    return new Vector2D(v1.x+v2.x, v1.y+v2.y);
  }

  public Vector2D sub(float _x, float _y) {
    return new Vector2D(this.x-_x, this.y-_y);
  }

  public Vector2D sub(Vector2D v) {
    return new Vector2D(this.x-v.x, this.y-v.y);
  }

  static public Vector2D sub(Vector2D v1, Vector2D v2) {
    return new Vector2D(v1.x-v2.x, v1.y-v2.y);
  }

  public Vector2D mult(float n) {
    return new Vector2D(this.x*n, this.y*n);
  }

  static public Vector2D mult(Vector2D v, float n) {
    return new Vector2D(v.x*n, v.y*n);
  }

  public Vector2D div(float n) {
    return new Vector2D(this.x/n, this.y/n);
  }

  static public Vector2D div(Vector2D v, float n) {
    return new Vector2D(v.x/n, v.y/n);
  }

  public float dist(Vector2D v) {
    float dx = this.x - v.x;
    float dy = this.y - v.y;
    return (float) Math.sqrt(dx*dx + dy+dy);
  }

  static public float dist(Vector2D v1, Vector2D v2) {
    float dx = v1.x - v2.x;
    float dy = v1.y - v2.y;
    return (float) Math.sqrt(dx*dx + dy+dy);
  }

  public float dot(Vector2D v) {
    return this.x*v.x + this.y*v.y;
  }

  static public float dot(Vector2D v1, Vector2D v2) {
    return v1.x*v2.x + v1.y*v2.y;
  }

  public Vector3D cross(Vector2D v) {
    return new Vector3D(0.0f, 0.0f, this.x*v.y - v.x*this.y);
  }

  static public Vector3D cross(Vector2D v1, Vector2D v2) {
    return new Vector3D(0.0f, 0.0f, v1.x*v2.y - v2.x*v1.y);
  }

  public Vector2D negative() {
    return new Vector2D(-this.x, -this.y);
  }

  public Vector2D normalize() {
    float m = this.mag();
    return (m == 0 || m == 1 ? new Vector2D(this) : this.div(m));
  }

  public Vector2D setMag(float len) {
    return this.normalize().mult(len);
  }

  @Deprecated
    static public Vector2D setMag(Vector2D v, float len) {
    return v.normalize().mult(len);
  }

  public Vector2D limit(float max) {
    if (this.magSq() <= max*max) return new Vector2D(this);
    return new Vector2D(this).normalize().mult(max);
  }

  @Deprecated
    static public Vector2D limit(Vector2D v, float max) {
    if (v.magSq() <= max*max) return new Vector2D(v);
    return new Vector2D(v).normalize().mult(max);
  }

  public float heading() {
    return (float) Math.atan2(this.y, this.x);
  }

  public Vector2D rotate(float theta) {
    return new Vector2D(
      (float) (this.x*Math.cos(theta) - this.y*Math.sin(theta)), 
      (float) (this.x*Math.sin(theta) + this.y*Math.cos(theta))
      );
  }

  public Vector2D lerp(Vector2D end, float amt) {
    return new Vector2D(
      this.x + (this.x - end.x) * amt, 
      this.y + (this.y - end.y) * amt
      );
  }

  static public Vector2D lerp(Vector2D start, Vector2D end, float amt) {
    return new Vector2D(
      start.x + (start.x - end.x) * amt, 
      start.y + (start.y - end.y) * amt
      );
  }

  static public float angleBetween(Vector2D v1, Vector2D v2) {
    if (v1.x == 0 && v1.y == 0) return 0.0f;
    if (v2.x == 0 && v2.y == 0) return 0.0f;
    double amt = Vector2D.dot(v1, v2) / (v1.mag() * v2.mag());
    if (amt <= -1) {
      return (float) Math.PI;
    } else if (amt >= 1) {
      return 0;
    }
    return (float) Math.acos(amt);
  }

  public Vector2D reflect(Vector2D n) {
    return Vector2D.sub(this, n.mult(2 * Vector2D.dot(this, n)));
  }

  static public Vector2D reflect(Vector2D v, Vector2D n) {
    return Vector2D.sub(v, n.mult(2 * Vector2D.dot(v, n)));
  }

  public Vector2D refract(Vector2D n, float eta) {
    float dot = Vector2D.dot(this, n);
    float d   = 1 - eta*eta * (1 - dot*dot);
    if (0 < d) {
      return Vector2D.sub(
        Vector2D.sub(this, n.mult(dot)).mult(eta), 
        n.mult((float) Math.sqrt(d))
        );
    }
    return this.reflect(n);
  }
  
  static public Vector2D refract(Vector2D v, Vector2D n, float eta) {
    float dot = Vector2D.dot(v, n);
    float d   = 1 - eta*eta * (1 - dot*dot);
    if (0 < d) {
      return Vector2D.sub(
        Vector2D.sub(v, n.mult(dot)).mult(eta), 
        n.mult((float) Math.sqrt(d))
        );
    }
    return Vector2D.reflect(v, n);
  }

  @Override
    public String toString() {
    return "[ " + this.x + ", " + this.y + " ]";
  }

  @Override
    public boolean equals(Object obj) {
    if (!(obj instanceof Vector2D)) {
      return false;
    }
    final Vector2D p = (Vector2D) obj;
    return x == p.x && y == p.y;
  }

  @Override
    public int hashCode() {
    int result = 1;
    result = 31 * result + Float.floatToIntBits(this.x);
    result = 31 * result + Float.floatToIntBits(this.y);
    result = 31 * result + Float.floatToIntBits(0.0f);
    return result;
  }
}