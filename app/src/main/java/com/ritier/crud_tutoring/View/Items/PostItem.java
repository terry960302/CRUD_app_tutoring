package com.ritier.crud_tutoring.View.Items;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ritier.crud_tutoring.R;
import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;

public class PostItem extends Item {

    private int id;
    private String title;
    private String desc;

    public PostItem(int id, String title, String desc){
        this.id = id;
        this.title = title;
        this.desc = desc;
    }
    @Override
    public void bind(@NonNull GroupieViewHolder viewHolder, int position) {
        //제목
        TextView tvTitle = viewHolder.itemView.findViewById(R.id.tv_title);
        tvTitle.setText(title);

        //내용
        TextView tvDesc = viewHolder.itemView.findViewById(R.id.tv_desc);
        tvDesc.setText(desc);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_read_item;
    }
}
