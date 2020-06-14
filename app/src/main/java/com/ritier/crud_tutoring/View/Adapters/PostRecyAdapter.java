package com.ritier.crud_tutoring.View.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ritier.crud_tutoring.Dao.PostDaoImpl;
import com.ritier.crud_tutoring.Models.Post;
import com.ritier.crud_tutoring.R;

import java.util.ArrayList;
import java.util.List;

public class PostRecyAdapter extends RecyclerView.Adapter<PostRecyAdapter.PostRecyViewHolder> {

    private Context context;
    public List<Post> posts;
    private PostDaoImpl postDao;

    public PostRecyAdapter(Context context, List<Post> posts, PostDaoImpl postDao){
        this.context = context;
        this.posts = posts;
        this.postDao = postDao;
    }

    @NonNull
    @Override
    public PostRecyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_read_item, parent, false);
        return new PostRecyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostRecyViewHolder holder, final int position) {
        holder.tvTitle.setText(posts.get(position).getTitle());
        holder.tvDesc.setText(posts.get(position).getDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostRecyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvDesc;

        public PostRecyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }

    public void addItem(Post post){
        posts.add(post);
        notifyItemChanged(-1);
    }

    private void showDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Post 삭제");
        builder.setMessage("정말 삭제하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        postDao.deletePost(posts.get(position).getId());
                        posts.remove(position);
                        notifyDataSetChanged();

                        dialog.cancel();
                        Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }
}
