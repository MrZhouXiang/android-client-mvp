package puyuntech.com.beihai.presenter;

public interface IUpdateUIListener {
	/**
	 * 根据类型获取UI数据
	 * @param type
	 * @return
	 */
	public Object getValue(Enum type);
	/**
	 * 根据类型修改UI
	 * @param params
	 * @param type
	 */
	public void updateUI(Object params, Enum type);

}
