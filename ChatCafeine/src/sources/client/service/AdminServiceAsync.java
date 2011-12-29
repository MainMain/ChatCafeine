/**
 * 
 */
package sources.client.service;

import java.util.ArrayList;

import sources.client.model.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author : Johan
 *
 */
public interface AdminServiceAsync {

	void getAllUsers(AsyncCallback<ArrayList<User>> callback);

}
