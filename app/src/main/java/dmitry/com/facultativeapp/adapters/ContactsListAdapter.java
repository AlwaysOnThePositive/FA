package dmitry.com.facultativeapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tomash.androidcontacts.contactgetter.entity.ContactData;
import com.tomash.androidcontacts.contactgetter.entity.PhoneNumber;

import java.util.List;

import dmitry.com.facultativeapp.R;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {

    //Адаптер который отвечает за отрисовку списка контактов

    private List<ContactData> contactDataList;


    public ContactsListAdapter(List<ContactData> contactDataList) {
        this.contactDataList = contactDataList;
    }


    //Описываем один элемент списка
    public class ContactViewHolder extends RecyclerView.ViewHolder {

        View allView;
        TextView nameTV;
        TextView phoneTV;

        public ContactViewHolder(View itemView) {
            super(itemView);
            allView = itemView;
            nameTV = itemView.findViewById(R.id.itemContactNameTV);
            phoneTV = itemView.findViewById(R.id.itemContactPhoneTV);
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Инфлейт разметки
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    //Отрисовка одного элемента списка
    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.nameTV.setText(contactDataList.get(position).getNameData().getFullName());
        if (contactDataList.get(position).getPhoneList().size() > 0) {
            holder.phoneTV.setText(contactDataList.get(position).getPhoneList().get(0).getMainData());
        } else {
            holder.phoneTV.setText("нет номера");
        }
    }

    //Количество элементов списка
    @Override
    public int getItemCount() {
        if (contactDataList != null) {
            return contactDataList.size();
        } else {
            return 0;
        }
    }
}
