package demo.database;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alhamdulillah on 10/27/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.TestViewHolder> {
    List<KTP> listKTP = new ArrayList<KTP>();
    public MyAdapter(List<KTP> listKTP) {
        this.listKTP = listKTP;
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        TestViewHolder mViewHolder = new TestViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, final int position) {
        holder.tvId.setText(String.valueOf(listKTP.get(position).getId()));
        holder.tvNama.setText(listKTP.get(position).getNama());
        holder.tvTelpn.setText(listKTP.get(position).getTelpn());
        holder.tvAgama.setText(listKTP.get(position).getAgama());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(),String.valueOf(position),Toast.LENGTH_LONG).show();
                Intent mIntent = new Intent(view.getContext(),DetailActivity.class);
                mIntent.putExtra("id",listKTP.get(position).getId());
                mIntent.putExtra("nama",listKTP.get(position).getNama());
                mIntent.putExtra("telpn",listKTP.get(position).getTelpn());
                mIntent.putExtra("agama",listKTP.get(position).getAgama());
                view.getContext().startActivity(mIntent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return listKTP.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvNama, tvTelpn, tvAgama;
        public TestViewHolder(View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.tvId2);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama2);
            tvTelpn = (TextView) itemView.findViewById(R.id.tvTelpn2);
            tvAgama = (TextView) itemView.findViewById(R.id.tvAgama2);
        }
    }
}
