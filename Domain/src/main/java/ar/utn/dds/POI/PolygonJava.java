package ar.utn.dds.POI;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.geodds.NumberUtils;

/**
 * Define una zona conformada por vértices (un conjunto de puntos)<br>
 * <br>
 * <b>Importante:</b> cuando se define una zona no hay que definir el vértice final igual al inicial<br>
 * <br>
 * @author DDS
 */
@Accessors
@SuppressWarnings("all")
@Entity
public class PolygonJava {
	
	@Id
	@GeneratedValue
	private Long id;
	
  @ElementCollection(targetClass = PointJava.class, fetch=FetchType.LAZY)
  protected List<PointJava> surface;
  
  /**
   * Constructor default, obliga luego a agregar los puntos manualmente mediante el mensaje add(Point point). <br><br>
   * Para trabajar con un polígono inmutable, no debe usarse este constructor ni el add posterior.<br>
   */
  
  public PolygonJava() {
    ArrayList<PointJava> _arrayList = new ArrayList<PointJava>();
    this.surface = _arrayList;
  }
  
  public boolean add(final PointJava punto) {
    return this.surface.add(punto);
  }
  
  /**
   * Constructor que le pasa un conjunto de puntos que definen el polígono
   */
  public PolygonJava(final List<PointJava> points) {
    this.surface = points;
  }
  
  /**
   * Convierte un punto a un polígono conformado inicialmente por este punto. <br>
   * No compatible con la idea de un polígono inmutable.<br>
   */
  public static PolygonJava asPolygon(final PointJava point) {
    return new PolygonJava(((List<PointJava>) Collections.<PointJava>unmodifiableList(CollectionLiterals.<PointJava>newArrayList(point))));
  }
  
  /**
   * Determina si un punto está dentro de un polígono
   * Para la explicación véase http://erich.realtimerendering.com/ptinpoly/
   * @Deprecated Usar isInside
   */
  public boolean isInsideOld(final PointJava point) {
    boolean _xblockexpression = false;
    {
      int counter = 0;
      PointJava point1 = this.surface.get(0);
      final int N = this.surface.size();
      for (int i = 1; (i <= N); i++) {
        {
          PointJava point2 = this.surface.get((i % N));
          boolean _intersects = point.intersects(point1, point2);
          if (_intersects) {
            counter++;
          }
          point1 = point2;
        }
      }
      _xblockexpression = NumberUtils.even(counter);
    }
    return _xblockexpression;
  }
  
  /**
   * Función mejorada para determinar si un punto está en el polígono
   */
  public boolean isInside(final PointJava point) {
    boolean _xblockexpression = false;
    {
      final int N = this.surface.size();
      int j = (N - 1);
      boolean oddNodes = false;
      double x = point.longitude();
      double y = point.latitude();
      for (int i = 0; (i < N); i++) {
        {
          PointJava _get = this.surface.get(i);
          final double verticeIY = _get.latitude();
          PointJava _get_1 = this.surface.get(i);
          final double verticeIX = _get_1.longitude();
          PointJava _get_2 = this.surface.get(j);
          final double verticeJY = _get_2.latitude();
          PointJava _get_3 = this.surface.get(j);
          final double verticeJX = _get_3.longitude();
          if (((((verticeIY < y) && (verticeJY >= y)) || ((verticeJY < y) && (verticeIY >= y))) && ((verticeIX <= x) || (verticeJX <= x)))) {
            if (((verticeIX + (((y - verticeIY) / (verticeJY - verticeIY)) * (verticeJX - verticeIX))) < x)) {
              oddNodes = (!oddNodes);
            }
          }
          j = i;
        }
      }
      _xblockexpression = oddNodes;
    }
    return _xblockexpression;
  }
  
  /**
   * Indica si un punto es alguno de los vértices del polígono
   */
  public boolean pointOnVertex(final PointJava point) {
    return this.surface.contains(point);
  }
  
  @Pure
  public List<PointJava> getSurface() {
    return this.surface;
  }
  
  public void setSurface(final List<PointJava> surface) {
    this.surface = surface;
  }

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}
  
  
}