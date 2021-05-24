package io.kevinjad.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "CrimeListFragment"

class CrimeListFragment : Fragment() {

    private lateinit var crimeRecyclerView: RecyclerView
    private lateinit var crimeAdapter: CrimeAdapter

    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"Total crimes: ${crimeListViewModel.crimes}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_crime_list,container,false)
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()
        return view
    }

    private fun updateUI(){
        val crimes = crimeListViewModel.crimes
        crimeAdapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = crimeAdapter
    }

    private inner class CrimeHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

        lateinit var crime: Crime
        val crimeTitle: TextView = itemView.findViewById(R.id.crime_title)
        val crimeDate: TextView = itemView.findViewById(R.id.crime_date)
        val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)
        fun bind(crime: Crime){
            this.crime = crime
            crimeTitle.text = this.crime.title
            crimeDate.text = this.crime.date.toString()

            solvedImageView.visibility = if(this.crime.isSolved){
                View.VISIBLE
            }
            else{
                View.GONE
            }
        }

        override fun onClick(v: View?) {
            Toast.makeText(context,"${this.crime.title} is pressed",Toast.LENGTH_SHORT).show()
        }
    }

    private inner class CrimeAdapter(var crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val view = layoutInflater.inflate(R.layout.list_item_crime,parent,false)
            return CrimeHolder(view)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            holder.bind(crimes[position])
        }

        override fun getItemCount(): Int = crimes.size

    }

    companion object {
        fun newInstance(): CrimeListFragment{
            return CrimeListFragment()
        }
    }

}