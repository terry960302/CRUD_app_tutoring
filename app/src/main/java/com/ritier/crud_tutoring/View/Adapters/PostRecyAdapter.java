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
import com.ritier.crud_tutoring.Dao.TeammateDaoImpl;
import com.ritier.crud_tutoring.Models.Teammate;
import com.ritier.crud_tutoring.R;
import java.util.List;

public class PostRecyAdapter extends RecyclerView.Adapter<PostRecyAdapter.PostRecyViewHolder> {

    private Context context;
    public List<Teammate> teammates;
    private TeammateDaoImpl teammateDao;

    //생성자
    public PostRecyAdapter(Context context, List<Teammate> teammates, TeammateDaoImpl teammateDao){
        this.context = context;
        this.teammates = teammates;
        this.teammateDao = teammateDao;
    }

    //xml 파일의 id 요소를 가져올 수 있게 뷰 세팅
    @NonNull
    @Override
    public PostRecyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_read_item, parent, false);
        return new PostRecyViewHolder(view);
    }

    //xml 파일 id요소를 자바 코드와 연동
    @Override
    public void onBindViewHolder(@NonNull PostRecyViewHolder holder, final int position) {
        Teammate item = teammates.get(position);

        holder.tvBackNumber.setText(item.getBackNumber());
        holder.tvName.setText(item.getName());
        holder.tvLeftRight.setText(item.getLeftRight());
        holder.tvAge.setText(item.getAge());

        //TODO : 클릭시 행동들
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "그냥 클릭", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Toast.makeText(context, "길게 클릭", Toast.LENGTH_SHORT).show();
                showDialog(position);
                return false;
            }
        });
    }

    // recyclerview  아이템을 몇개를 보여줄 지 설정
    @Override
    public int getItemCount() {
        return teammates.size();
    }

    // 아이템 속 요소 설정
    static class PostRecyViewHolder extends RecyclerView.ViewHolder{

        TextView tvBackNumber;
        TextView tvName;
        TextView tvLeftRight;
        TextView tvAge;

        PostRecyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBackNumber = itemView.findViewById(R.id.tv_backNumber);
            tvName = itemView.findViewById(R.id.tv_name);
            tvLeftRight = itemView.findViewById(R.id.tv_leftRight);
            tvAge = itemView.findViewById(R.id.tv_age);
        }
    }

    // 총 아이템에서 아이템 하나 추가
    public void addItem(Teammate post){
        teammates.add(post);
        notifyItemChanged(-1);
    }

    public void clearItems(){
        teammates.clear();
        notifyDataSetChanged();
    }

    // 아이템 클릭 시 팝업 창
    private void showDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Post 삭제");
        builder.setMessage("정말 삭제하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        teammateDao.deleteTeammate(teammates.get(position).getId());

                        teammates.remove(position);
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
