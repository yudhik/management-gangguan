package com.iconplus.gangguan.viewobject;

import java.io.Serializable;
import java.util.List;

public class AvailableMenu implements Serializable {

  private static final long serialVersionUID = -6930891337166637801L;

  private String id;
  private String label;
  private String url;
  private List<AvailableMenu> childMenu;

  public AvailableMenu() {
    // nothing to do here
  }

  public AvailableMenu(String id, String label, String url, List<AvailableMenu> childMenu) {
    this.id = id;
    this.label = label;
    this.url = url;
    this.childMenu = childMenu;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AvailableMenu other = (AvailableMenu) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public List<AvailableMenu> getChildMenu() {
    return childMenu;
  }

  public String getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  public void setChildMenu(List<AvailableMenu> childMenu) {
    this.childMenu = childMenu;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}