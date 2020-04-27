/**2017年10月19日
 * 
 */
package mose.sys.vo;


import mose.core.page.PageSearchVO;

/**
 *  字典信息查询条件
 * 
 * @author: mose
 * @date: 2017年10月19日
 */
public class SysReleaseSearchVO extends PageSearchVO {

	private String name;              //系统名称
	private String releaseDate;       //发布日期
	private String releaseBy;         //发布人
	private String version;           //版本号
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getReleaseBy() {
		return releaseBy;
	}
	public void setReleaseBy(String releaseBy) {
		this.releaseBy = releaseBy;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	//模糊查询
    public String getNameStr() {
        return "%" + name + "%";
    }
    
    public String getVersionStr() {
        return "%" + version + "%";
    }
    
    
    public String getReleaseByStr() {
        return "%" + releaseBy + "%";
    }
    
    public String getReleaseDateStr() {
        return "%" + releaseDate + "%";
    }
    
	
	
	@Override
	public String toString() {
		return "SysReleaseSearchVO [name=" + name + ", releaseDate=" + releaseDate + ", releaseBy=" + releaseBy
				+ ", version=" + version + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
