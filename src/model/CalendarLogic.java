package model;

import java.util.Calendar;

public class CalendarLogic {

	//可変長引数が0の時は現在の年月を表示(初期画面)、2つ渡された場合は、指定の年月に変更(先月、、翌月時のカレンダー表示）に
	//対応するために可変長引数にしている。
	public CalendarBeans execute(int... args) {

		CalendarBeans beans = new CalendarBeans();
		Calendar c = Calendar.getInstance();


		if (args.length == 2) {
			c.set(Calendar.YEAR, args[0]);
			c.set(Calendar.MONTH, args[1]);
		}


		beans.setYear(c.get(Calendar.YEAR));
		beans.setMonth(c.get(Calendar.MONTH) + 1);

		c.set(Calendar.DATE, 1);
		//月の最初の空白を求める
		int start = c.get(Calendar.DAY_OF_WEEK) - 1;
		System.out.println(start);
		//当月の日数を求める
		int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		//当月の最終日の曜日を求めるために、日付けを最終日に変更
		c.set(Calendar.DATE, days);
		//最終週の空白数を算出
		int end = 7 - c.get(Calendar.DAY_OF_WEEK);
		//全ての要素数を算出
		int total = start + days + end;
		int rows = total / 7;
		String[][] date = new String[rows][7];

		//現在見ているのが、今月かどうかを確認
	//	Calendar now = Calendar.getInstance();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < 7; j++) {
				if (i == 0 && j < start || i == rows - 1 && j >= (7 - end)) {
					date[i][j] = "";
				} else {
					//実際の日付けを挿入
					//最初に空白を含む日数を求める。
					//(その月の何週目*7)=x ：xはその週時点での日数を算出
					//(その週の何日目）+1=y:+1するのはiが0を含む為。
					//空白分を引く。
					int dates = i * 7 + j + 1 - start;
					date[i][j] = String.valueOf(dates);


				}
			}
		}
		beans.setDate(date);
		return beans;

	}

	/**
	 * カレンダーの年月をインスタンスにセット
	 * @return
	 */

	public CalendarBeans execute2() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);

		CalendarBeans beans = new CalendarBeans();
		beans.setYear(year);
		beans.setMonth(month);

		return beans;

	}
}
