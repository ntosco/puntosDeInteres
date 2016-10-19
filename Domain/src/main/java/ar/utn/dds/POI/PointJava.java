package ar.utn.dds.POI;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbar.geodds.NumberUtils;

import com.google.gson.annotations.Expose;

/**
 * Representa un punto dentro de unas coordenadas
 * <br>
 * 
 * @author DDS
 */
@SuppressWarnings("all")
@Entity
public class PointJava {
	

@Id
@GeneratedValue
@Expose private int id;
  private BigDecimal x;
  
  private BigDecimal y;
  
  /**
   * Constructor default para que lo pueda usar algun
   * framework
   */
  
  public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setX(BigDecimal x) {
		this.x = x;
	}


	public void setY(BigDecimal y) {
		this.y = y;
	}

  
  public PointJava() {
  }
  
  
  /**
   * Indica la latitud del punto (su abscisa)
   */
  public double latitude() {
    return this.getX();
  }
  
  /**
   * Indica la longitud del punto (su ordenada)
   */
  public double longitude() {
    return this.getY();
  }
  
  /**
   * Distancia en kilómetros con respecto a otro punto
   * 
   * @author Internet
   */
  public double distance(final PointJava anotherPoint) {
    double _xblockexpression = (double) 0;
    {
      final int earthRadius = 6371;
      double _latitude = anotherPoint.latitude();
      double _latitude_1 = this.latitude();
      double _minus = (_latitude - _latitude_1);
      final double deltaLatitude = this.toRadian(_minus);
      double _longitude = anotherPoint.longitude();
      double _longitude_1 = this.longitude();
      double _minus_1 = (_longitude - _longitude_1);
      final double deltaLongitude = this.toRadian(_minus_1);
      double _sin = Math.sin((deltaLatitude / 2));
      double _sin_1 = Math.sin((deltaLatitude / 2));
      double _multiply = (_sin * _sin_1);
      double _latitude_2 = this.latitude();
      double _radian = this.toRadian(_latitude_2);
      double _cos = Math.cos(_radian);
      double _latitude_3 = anotherPoint.latitude();
      double _radian_1 = this.toRadian(_latitude_3);
      double _cos_1 = Math.cos(_radian_1);
      double _multiply_1 = (_cos * _cos_1);
      double _sin_2 = Math.sin((deltaLongitude / 2));
      double _multiply_2 = (_multiply_1 * _sin_2);
      double _sin_3 = Math.sin((deltaLongitude / 2));
      double _multiply_3 = (_multiply_2 * _sin_3);
      final double a = (_multiply + _multiply_3);
      double _sqrt = Math.sqrt(a);
      double _sqrt_1 = Math.sqrt((1 - a));
      double _atan2 = Math.atan2(_sqrt, _sqrt_1);
      final double distanceAngular = (2 * _atan2);
      _xblockexpression = (earthRadius * distanceAngular);
    }
    return _xblockexpression;
  }
  
  /**
   * Conversión de un punto a radianes en base al ángulo que describe
   */
  public double toRadian(final double angle) {
    return ((Math.PI * angle) / 180.0);
  }
  
  /**
   * Conversión de un punto a grados en base al ángulo que describe
   */
  public double toDegree(final double angle) {
    return (angle * (180.0 / Math.PI));
  }
  
  private double xIntersection(final PointJava p1, final PointJava p2) {
    double _latitude = this.latitude();
    double _latitude_1 = p1.latitude();
    double _minus = (_latitude - _latitude_1);
    double _longitude = p2.longitude();
    double _longitude_1 = p1.longitude();
    double _minus_1 = (_longitude - _longitude_1);
    double _multiply = (_minus * _minus_1);
    double _latitude_2 = p2.latitude();
    double _latitude_3 = p1.latitude();
    double _minus_2 = (_latitude_2 - _latitude_3);
    double _divide = (_multiply / _minus_2);
    double _longitude_2 = p1.longitude();
    return (_divide + _longitude_2);
  }
  
  /**
   * Indica si un punto está en el medio de dos puntos
   */
  public boolean intersects(final PointJava point, final PointJava point2) {
    double _latitude = point.latitude();
    double _latitude_1 = this.latitude();
    double _latitude_2 = point2.latitude();
    double _min = NumberUtils.min(_latitude_1, _latitude_2);
    boolean _greaterThan = (_latitude > _min);
    if (_greaterThan) {
      double _latitude_3 = point.latitude();
      double _latitude_4 = this.latitude();
      double _latitude_5 = point2.latitude();
      double _max = NumberUtils.max(_latitude_4, _latitude_5);
      boolean _lessEqualsThan = (_latitude_3 <= _max);
      if (_lessEqualsThan) {
        double _longitude = point.longitude();
        double _longitude_1 = this.longitude();
        double _longitude_2 = point2.longitude();
        double _max_1 = NumberUtils.max(_longitude_1, _longitude_2);
        boolean _lessEqualsThan_1 = (_longitude <= _max_1);
        if (_lessEqualsThan_1) {
          double _latitude_6 = this.latitude();
          double _latitude_7 = point2.latitude();
          boolean _notEquals = (_latitude_6 != _latitude_7);
          if (_notEquals) {
            final double intersection = point.xIntersection(this, point2);
            boolean _or = false;
            double _longitude_3 = this.longitude();
            double _longitude_4 = point2.longitude();
            boolean _equals = (_longitude_3 == _longitude_4);
            if (_equals) {
              _or = true;
            } else {
              double _longitude_5 = point.longitude();
              boolean _lessEqualsThan_2 = (_longitude_5 <= intersection);
              _or = _lessEqualsThan_2;
            }
            if (_or) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
  
  /**
   * Representación de un punto como un String
   */
  @Override
  public String toString() {
    return ((("x: " + this.x) + ", y: ") + this.y);
  }
  
  /**
   * Public accessors para que los puedan usar algun
   * framework
 * @return 
   */
  
  public PointJava setXY(final double aX, final double aY) {
	    BigDecimal _bigDecimalX = new BigDecimal(aX);
	    this.x = _bigDecimalX;
	    BigDecimal _bigDecimalY = new BigDecimal(aY);
	    this.y = _bigDecimalY;
	    return this;
	  }
  
  public double getX() {
    return this.x.doubleValue();
  }
  
  public void setX(final double aX) {
    BigDecimal _bigDecimal = new BigDecimal(aX);
    this.x = _bigDecimal;
  }
  
  public double getY() {
    return this.y.doubleValue();
  }
  
  public void setY(final double aY) {
    BigDecimal _bigDecimal = new BigDecimal(aY);
    this.y = _bigDecimal;
  }
}
