package expense.tracker.controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.rapidoid.annotation.Controller;
import org.rapidoid.annotation.POST;
import org.rapidoid.http.Req;
import org.rapidoid.u.U;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import expense.tracker.Main;
import expense.tracker.dao.Transaction;
import expense.tracker.database.SQLite;

@Controller
public class TransactionCtrl {

	@POST("/dtTransactions")
	public List<Transaction> getList(Req req) throws Exception {
		return getTransactions(req);
		/*System.out.println(dt);
		List<Transaction> list = Main.db.getRecords(new SQLite.CustomQueryBuilder() {
			@Override
			public <T> void build(QueryBuilder<T, String> builder) throws SQLException {
				Calendar calFrom = Calendar.getInstance();
				calFrom.add(Calendar.MONTH, -1);
				for(Column c : dt.getColumns()) {
					if(c.isSearchable() && c.getSearch().getValue() != null) {
						builder.where().like(c.getData(), c.getSearch().getValue());
					}
				}
				builder.offset(dt.getStart());
				builder.limit(dt.getLength());
				//Calendar calTo = Calendar.getInstance();
				//Where<T, String> where = builder.where();
				//where.between("dateTime", req.posted("from", calFrom.getTimeInMillis()), req.posted("to", calTo.getTimeInMillis()));
			}
		}, Transaction.class);
		return list;*/
	}
	
	@POST("/transaction")
	public Map<String, Object> transaction(Transaction t){
		try {
			Main.db.saveRecord(t, Transaction.class);
			return U.map("Status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			return U.map("Error", e.getMessage());
		}
	}
	
	@POST("/getTransactions")
	public List<Transaction> getTransactions(Req req) throws Exception {
		System.out.println(req.posted());
		List<Transaction> list = Main.db.getRecords(new SQLite.CustomQueryBuilder() {
			@Override
			public <T> void build(QueryBuilder<T, String> builder) throws SQLException {
				Calendar calFrom = Calendar.getInstance();
				calFrom.add(Calendar.YEAR, -1);
				Calendar calTo = Calendar.getInstance();
				Where<T, String> where = builder.where();
				where.between("dateTime", req.posted("from", calFrom.getTimeInMillis()), req.posted("to", calTo.getTimeInMillis()));
			}
		}, Transaction.class);
		return list;
	}
}
