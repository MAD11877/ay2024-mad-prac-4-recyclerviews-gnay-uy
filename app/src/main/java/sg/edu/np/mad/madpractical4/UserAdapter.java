package sg.edu.np.mad.madpractical4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<User> userList;
    private Context context;

    public UserAdapter(ArrayList<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        return new UserViewHolder(view);
    }

    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User currentUser = userList.get(position);
        holder.nameTextView.setText(currentUser.name);
        holder.descriptionTextView.setText(currentUser.description);
        holder.imageView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(currentUser.name);
            builder.show();
        });
        holder.viewButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("user", currentUser);
            context.startActivity(intent);
        });
        holder.followButton.setOnClickListener(v -> {
            currentUser.followed = !currentUser.followed;
            holder.followButton.setText(currentUser.followed ? "Unfollow" : "Follow");
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}