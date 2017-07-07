class Vector3D {

  /* Field */

  // the x component of the vector.
  float x;

  // the y component of the vector.
  float y;

  // the z component of the vector.
  float z;


  /* Constructor */

  // for an empty vector
  public Vector3D() {
    this.x = 0;
    this.y = 0;
    this.z = 0;
  }

  // for a 3D vector
  public Vector3D(float _x, float _y, float _z) {
    this.x = _x;
    this.y = _y;
    this.z = _z;
  }

  // for a 3D vector duplicate 2D vector
  public Vector3D(Vector2D v) {
    this.x = v.x;
    this.y = v.y;
    this.z = 0;
  }

  // for a 3D vector duplicate 3D vector
  public Vector3D(Vector3D v) {
    this.x = v.x;
    this.y = v.y;
    this.z = v.z;
  }


  /* Method */

  // return random 3D unit vector
  static public Vector3D random() {
    double angle1 = Math.random() * Math.PI * 2;
    double angle2 = Math.random() * Math.PI * 2;
    return new Vector3D(
      (float)(Math.cos(angle1) * Math.cos(angle2)), 
      (float)(Math.sin(angle1) * Math.cos(angle2)), 
      (float)(Math.sin(angle2))
      ).normalize();
  }

  // return magnitude of vector
  public float mag() {
    return (float) Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
  }

  // return square magnitude of vector
  public float magSq() {
    return (this.x*this.x + this.y*this.y + this.z*this.z);
  }

  public Vector3D add(float _x, float _y, float _z) {
    this.x += _x;
    this.y += _y;
    this.z += _z;
    return this;
  }

  public Vector3D add(Vector3D... vec) {
    for (Vector3D v : vec) {
      this.x += v.x;
      this.y += v.y;
      this.z += v.z;
    }
    return this;
  }

  static public Vector3D add(Vector3D target, Vector3D... vec) {
    Vector3D result = new Vector3D(target);
    for (Vector3D v : vec) {
      result.x += v.x;
      result.y += v.y;
      result.z += v.z;
    }
    return result;
  }

  public Vector3D sub(float _x, float _y, float _z) {
    this.x -= _x;
    this.y -= _y;
    this.z -= _z;
    return this;
  }

  public Vector3D sub(Vector3D v) {
    this.x -= v.x;
    this.y -= v.y;
    this.z -= v.z;
    return this;
  }

  static public Vector3D sub(Vector3D target, Vector3D... vec) {
    Vector3D result = new Vector3D(target);
    for (Vector3D v : vec) {
      result.x += v.x;
      result.y += v.y;
      result.z += v.z;
    }
    return result;
  }

  public Vector3D mult(float n) {
    this.x *= n;
    this.y *= n;
    this.z *= n;
    return this;
  }

  static public Vector3D mult(Vector3D v, float n) {
    return new Vector3D(v.x*n, v.y*n, v.z*n);
  }

  public Vector3D div(float n) {
    if (n == 0) throw new ArithmeticException("/ by zero");
    this.x /= n;
    this.y /= n;
    this.z /= n;
    return this;
  }

  static public Vector3D div(Vector3D v, float n) {
    return new Vector3D(v.x/n, v.y/n, v.z/n);
  }
  
  // return distance of this vector and another vector
  public float dist(Vector3D v) {
    float dx = this.x - v.x;
    float dy = this.y - v.y;
    float dz = this.z - v.z;
    return (float) Math.sqrt(dx*dx + dy*dy + dz*dz);
  }

  static public float dist(Vector3D v1, Vector3D v2) {
    float dx = v1.x - v2.x;
    float dy = v1.y - v2.y;
    float dz = v1.z - v2.z;
    return (float) Math.sqrt(dx*dx + dy*dy + dz*dz);
  }
  
  // return dot of this vector and another vector
  public float dot(Vector3D v) {
    return this.x*v.x + this.y*v.y + this.z*v.z;
  }

  static public float dot(Vector3D v1, Vector3D v2) {
    return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
  }
  
  // return cross vector of this vector and another vector
  public Vector3D cross(Vector3D v) {
    return new Vector3D(
      this.y*v.z - v.y*this.z, 
      this.z*v.x - v.z*this.x, 
      this.x*v.y - v.x*this.y
      );
  }

  static public Vector3D cross(Vector3D v1, Vector3D v2) {
    return new Vector3D(
      v1.y*v2.z - v2.y*v1.z, 
      v1.z*v2.x - v2.z*v1.x, 
      v1.x*v2.y - v2.x*v1.y
      );
  }
  
  // reverse vector
  public Vector3D negative() {
    this.x *= -1;
    this.y *= -1;
    this.z *= -1;
    return this;
  }

  static public Vector3D negative(Vector3D v) {
    return new Vector3D(-v.x, -v.y, -v.z);
  }
  
  // normalize(unit vector)
  public Vector3D normalize() {
    float m = this.mag();
    if (m != 0 || m != 1) this.div(m);
    return this;
  }

  static public Vector3D normalize(Vector3D v) {
    float m = v.mag();
    return (m == 0 || m == 1 ? new Vector3D(v) : v.div(m));
  }
  
  // change the magnitude without changing the direction
  public Vector3D setMag(float len) {
    return this.normalize().mult(len);
  }

  static public Vector3D setMag(Vector3D v, float len) {
    return Vector3D.normalize(v).mult(len);
  }
  
  // limit the magnitude of vector
  public Vector3D limit(float max) {
    if (this.magSq() > max*max) this.setMag(max);
    return this;
  }

  static public Vector3D limit(Vector3D v, float max) {
    if (v.magSq() <= max*max) return new Vector3D(v);
    return Vector3D.setMag(v, max);
  }

  // rotate vector around X axis
  public Vector3D rotateX(float theta) {
    this.y = (float) (this.y*Math.cos(theta) - this.z*Math.sin(theta));
    this.z = (float) (this.y*Math.sin(theta) + this.z*Math.cos(theta));
    return this;
  }

  static public Vector3D rotateX(Vector3D v, float theta) {
    return new Vector3D(
      v.x, 
      (float) (v.y*Math.cos(theta) - v.z*Math.sin(theta)), 
      (float) (v.y*Math.sin(theta) + v.z*Math.cos(theta))
      );
  }
  
  // rotate vector around Y axis
  public Vector3D rotateY(float theta) {
    this.x = (float) (this.z*Math.sin(theta) + this.x*Math.cos(theta));
    this.y = (float) (this.z*Math.sin(theta) - this.x*Math.sin(theta));
    return this;
  }

  static public Vector3D rotateY(Vector3D v, float theta) {
    return new Vector3D(
      (float) (v.z*Math.sin(theta) + v.x*Math.cos(theta)), 
      v.y, 
      (float) (v.z*Math.sin(theta) - v.x*Math.sin(theta))
      );
  }
  
  // rotate vector around Z axis
  public Vector3D rotateZ(float theta) {
    this.x = (float) (this.x*Math.cos(theta) - this.y*Math.sin(theta));
    this.y = (float) (this.x*Math.sin(theta) + this.y*Math.cos(theta));
    return this;
  }

  static public Vector3D rotateZ(Vector3D v, float theta) {
    return new Vector3D(
      (float) (v.x*Math.cos(theta) - v.y*Math.sin(theta)), 
      (float) (v.x*Math.sin(theta) + v.y*Math.cos(theta)), 
      v.z
      );
  }
  
  // rotate vector by X-Y-Z
  public Vector3D rotate(float alpha, float beta, float gamma) {
    return this.rotateX(alpha).rotateY(beta).rotateZ(gamma);
  }

  static public Vector3D rotate(Vector3D v, float alpha, float beta, float gamma) {
    return Vector3D.rotateX(v, alpha).rotateY(beta).rotateZ(gamma);
  }
  
  // roll vector by roll-pitch-yaw
  public Vector3D rolling(float roll, float pitch, float yaw) {
    float r_sin = (float) Math.sin(roll);
    float r_cos = (float) Math.cos(roll);
    float p_sin = (float) Math.sin(pitch);
    float p_cos = (float) Math.cos(pitch);
    float y_sin = (float) Math.sin(yaw);
    float y_cos = (float) Math.cos(yaw);
    float _x = this.x;
    float _y = this.y;
    float _z = this.z;
    this.x = (r_cos*p_cos) * _x + (r_cos*p_sin*y_sin-r_sin*y_cos) * _y + (r_cos*p_sin*y_cos+r_sin*y_sin) * _z;
    this.y = (r_sin*p_cos) * _x + (r_sin*p_sin*y_sin+r_cos*y_cos) * _y + (r_sin*p_sin*y_cos-r_cos*y_sin) * _z;
    this.z = (- 1 * p_sin) * _x +                   (p_cos*y_sin) * _y +                   (p_cos*y_cos) * _z;
    return this;
  }

  static public Vector3D rolling(Vector3D v, float roll, float pitch, float yaw) {
    float r_sin = (float) Math.sin(roll);
    float r_cos = (float) Math.cos(roll);
    float p_sin = (float) Math.sin(pitch);
    float p_cos = (float) Math.cos(pitch);
    float y_sin = (float) Math.sin(yaw);
    float y_cos = (float) Math.cos(yaw);
    return new Vector3D(
      (r_cos*p_cos) * v.x + (r_cos*p_sin*y_sin-r_sin*y_cos) * v.y + (r_cos*p_sin*y_cos+r_sin*y_sin) * v.z, 
      (r_sin*p_cos) * v.x + (r_sin*p_sin*y_sin+r_cos*y_cos) * v.y + (r_sin*p_sin*y_cos-r_cos*y_sin) * v.z, 
      (- 1 * p_sin) * v.x +                   (p_cos*y_sin) * v.y +                   (p_cos*y_cos) * v.z
      );
  }
  
  // linear interpolate the vector to another vector
  static public Vector3D lerp(Vector3D start, Vector3D end, float amt) {
    return new Vector3D(
      start.x + (start.x - end.x) * amt, 
      start.y + (start.y - end.y) * amt, 
      start.z + (start.z - end.z) * amt
      );
  }
  
  // return angle between one vector and antoher vector
  static public float angleBetween(Vector3D v1, Vector3D v2) {
    if (v1.x == 0 && v1.y == 0 && v1.z == 0 ) return 0.0f;
    if (v2.x == 0 && v2.y == 0 && v2.z == 0 ) return 0.0f;
    double amt = Vector3D.dot(v1, v2) / (v1.mag() * v2.mag());
    if (amt <= -1) {
      return (float) Math.PI;
    } else if (amt >= 1) {
      return 0;
    }
    return (float) Math.acos(amt);
  }
  
  // return the vector reflected by this vector
  public Vector3D reflect(Vector3D n) {
    return Vector3D.sub(this, n.mult(2 * Vector3D.dot(this, n)));
  }

  static public Vector3D reflect(Vector3D v, Vector3D n) {
    return Vector3D.sub(v, n.mult(2 * Vector3D.dot(v, n)));
  }
  
  // return the vector refracted by this vector(refractive index : eta)
  public Vector3D refract(Vector3D n, float eta) {
    float dot = Vector3D.dot(this, n);
    float d   = 1 - eta*eta * (1 - dot*dot);
    if (0 < d) {
      return Vector3D.sub(
        Vector3D.sub(this, n.mult(dot)).mult(eta), 
        n.mult((float) Math.sqrt(d))
        );
    }
    return this.reflect(n);
  }

  static public Vector3D refract(Vector3D v, Vector3D n, float eta) {
    float dot = Vector3D.dot(v, n);
    float d   = 1 - eta*eta * (1 - dot*dot);
    if (0 < d) {
      return Vector3D.sub(
        Vector3D.sub(v, n.mult(dot)).mult(eta), 
        n.mult((float) Math.sqrt(d))
        );
    }
    return Vector3D.reflect(v, n);
  }

  @Override
    public String toString() {
    return "[ " + this.x + ", " + this.y + ", " + this.z + " ]";
  }

  @Override
    public boolean equals(Object obj) {
    if (!(obj instanceof Vector3D)) {
      return false;
    }
    final Vector3D p = (Vector3D) obj;
    return x == p.x && y == p.y && z == p.z;
  }

  @Override
    public int hashCode() {
    int result = 1;
    result = 31 * result + Float.floatToIntBits(this.x);
    result = 31 * result + Float.floatToIntBits(this.y);
    result = 31 * result + Float.floatToIntBits(this.z);
    return result;
  }
}