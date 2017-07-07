package Vector;

import Vector3D;

class Vector2D {

  /* Field */
  
  // the x component of the vector
  float x;
  
  // the y component of the vector
  float y;
  
  /* Constructor */
  
  // for an empty vector
  public Vector2D() {
    this.x = 0;
    this.y = 0;
  }
  
  // for a 2D vector
  public Vector2D(float _x, float _y) {
    this.x = _x;
    this.y = _y;
  }
  
  // for a 2D vector duplicate 2D vector
  public Vector2D(Vector2D v) {
    this.x = v.x;
    this.y = v.y;
  }

  // return random 2D unit vector
  static public Vector2D random() {
    double angle = Math.random() * Math.PI * 2;
    return new Vector2D((float)Math.cos(angle), (float)Math.sin(angle));
  }
  
  // return new 2D unit vector from an angle
  static public Vector2D fromAngle(float angle) {
    return new Vector2D((float)Math.cos(angle), (float)Math.sin(angle));
  }
  
  // return magnitude of vector
  public float mag() {
    return (float) Math.sqrt(this.x*this.x + this.y*this.y);
  }
  
  // return square magnitude of vector
  public float magSq() {
    return (this.x*this.x + this.y*this.y);
  }

  public Vector2D add(float _x, float _y) {
    this.x += _x;
    this.y += _y;
    return this;
  }

  public Vector2D add(Vector2D... vec) {
    for (Vector2D v : vec) {
      this.x += v.x;
      this.y += v.y;
    }
    return this;
  }

  static public Vector2D add(Vector2D target, Vector2D... vec) {
    Vector2D result = new Vector2D(target);
    for (Vector2D v : vec) {
      result.x += v.x;
      result.y += v.y;
    }
    return result;
  }

  public Vector2D sub(float _x, float _y) {
    this.x -= _x;
    this.y -= _y;
    return this;
  }

  public Vector2D sub(Vector2D v) {
    this.x -= v.x;
    this.y -= v.y;
    return this;
  }

  static public Vector2D sub(Vector2D target, Vector2D... vec) {
    Vector2D result = new Vector2D(target);
    for (Vector2D v : vec) {
      result.x -= v.x;
      result.y -= v.y;
    }
    return result;
  }

  public Vector2D mult(float n) {
    this.x *= n;
    this.y *= n;
    return this;
  }

  static public Vector2D mult(Vector2D v, float n) {
    return new Vector2D(v.x*n, v.y*n);
  }

  public Vector2D div(float n) {
    if (n == 0) throw new ArithmeticException("/ by zero");
    this.x /= n;
    this.y /= n;
    return this;
  }

  static public Vector2D div(Vector2D v, float n) {
    if (n == 0) throw new ArithmeticException("/ by zero");
    return new Vector2D(v.x/n, v.y/n);
  }
  
  // return distance of this vector and another vector
  public float dist(Vector2D v) {
    float dx = this.x - v.x;
    float dy = this.y - v.y;
    return (float) Math.sqrt(dx*dx + dy*dy);
  }

  static public float dist(Vector2D v1, Vector2D v2) {
    float dx = v1.x - v2.x;
    float dy = v1.y - v2.y;
    return (float) Math.sqrt(dx*dx + dy*dy);
  }
  
  // return dot of this vector and another vector
  public float dot(Vector2D v) {
    return this.x*v.x + this.y*v.y;
  }

  static public float dot(Vector2D v1, Vector2D v2) {
    return v1.x*v2.x + v1.y*v2.y;
  }
  
  // return cross vector of this vector and another vector
  public Vector3D cross(Vector2D v) {
    return new Vector3D(0.0f, 0.0f, this.x*v.y - v.x*this.y);
  }

  static public Vector3D cross(Vector2D v1, Vector2D v2) {
    return new Vector3D(0.0f, 0.0f, v1.x*v2.y - v2.x*v1.y);
  }
  
  // reverse vector
  public Vector2D negative() {
    this.x *= -1;
    this.y *= -1;
    return this;
  }

  static public Vector2D negative(Vector2D v) {
    return new Vector2D(-v.x, -v.y);
  }
  
  // normalize(unit vector)
  public Vector2D normalize() {
    float m = this.mag();
    if (m != 0 && m != -1) {
      this.x /= m;
      this.y /= m;
    }
    return this;
  }

  static public Vector2D normalize(Vector2D v) {
    float m = v.mag();
    return (m == 0 || m == 1 ? new Vector2D(v) : v.div(m));
  }
  
  // change the magnitude without changing the direction
  public Vector2D setMag(float len) {
    return this.normalize().mult(len);
  }

  static public Vector2D setMag(Vector2D v, float len) {
    return Vector2D.normalize(v).mult(len);
  }
  
  // limit the magnitude of vector
  public Vector2D limit(float max) {
    if (this.magSq() > max*max) this.setMag(max);
    return this;
  }

  static public Vector2D limit(Vector2D v, float max) {
    if (v.magSq() <= max*max) return new Vector2D(v);
    return Vector2D.setMag(v, max);
  }
  
  // return the angle of rotation for this vector
  public float heading() {
    return (float) Math.atan2(this.y, this.x);
  }
  
  // rotate this vector
  public Vector2D rotate(float theta) {
    this.x = (float) (this.x*Math.cos(theta) - this.y*Math.sin(theta));
    this.y = (float) (this.x*Math.sin(theta) + this.y*Math.cos(theta));
    return this;
  }

  static public Vector2D rotate(Vector2D v, float theta) {
    return new Vector2D(
      (float) (v.x*Math.cos(theta) - v.y*Math.sin(theta)), 
      (float) (v.x*Math.sin(theta) + v.y*Math.cos(theta))
      );
  }
  
  // linear interpolate the vector to another vector
  static public Vector2D lerp(Vector2D start, Vector2D end, float amt) {
    return new Vector2D(
      start.x + (start.x - end.x) * amt, 
      start.y + (start.y - end.y) * amt
      );
  }
  
  // return angle between one vector and antoher vector
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

  // return the vector reflected by this vector
  public Vector2D reflect(Vector2D n) {
    return Vector2D.sub(this, n.mult(2 * Vector2D.dot(this, n)));
  }

  static public Vector2D reflect(Vector2D v, Vector2D n) {
    return Vector2D.sub(v, n.mult(2 * Vector2D.dot(v, n)));
  }

  // return the vector refracted by this vector(refractive index : eta)
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