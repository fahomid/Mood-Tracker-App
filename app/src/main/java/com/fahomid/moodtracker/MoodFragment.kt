package com.fahomid.moodtracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// MoodFragment class is responsible for displaying a message based on the user's mood
// and fetching a motivational quote from a remote API
class MoodFragment : Fragment() {

    // Variables to store the mood and UI components
    private var mood: String? = null
    private lateinit var messageTextView: TextView
    private lateinit var quoteTextView: TextView

    // onCreate method is called when the fragment is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mood = it.getString("USER_MOOD")  // Retrieve the mood passed in the fragment's arguments
        }
    }

    // onCreateView method is called to create and return the view hierarchy associated with the fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mood, container, false)

        // Initialize the TextViews for displaying the mood message and quote
        messageTextView = view.findViewById(R.id.messageTextView)
        quoteTextView = view.findViewById(R.id.quoteTextView)

        // Set the mood message based on the user's mood
        messageTextView.text = when (mood) {
            "Happy" -> "Keep smiling! Happiness is contagious."
            "Sad" -> "It's okay to feel sad. Take a deep breath and let it go."
            "Neutral" -> "Stay balanced and keep moving forward."
            else -> "Your mood is your choice. Choose wisely!"
        }

        // Fetch a motivational quote from a remote API and display it
        fetchQuote()

        // Return the inflated view
        return view
    }

    // fetchQuote method makes a network call to retrieve a random quote and display it
    private fun fetchQuote() {
        val call = RetrofitClient.instance.getRandomQuote()  // Make the API call using Retrofit
        call.enqueue(object : Callback<List<Quote>> {
            // onResponse is called when the API request is successful
            override fun onResponse(call: Call<List<Quote>>, response: Response<List<Quote>>) {
                if (response.isSuccessful) {
                    Log.d("API Response", response.body().toString())  // Log the response
                    val quote = response.body()?.get(0)  // Get the first quote from the response
                    val quoteMessage = getString(R.string.quote_message, quote?.q, quote?.a)  // Format the quote message
                    quoteTextView.text = quoteMessage  // Display the quote in the TextView
                } else {
                    quoteTextView.text = getString(R.string.could_not_retrieve_quote)  // Display an error message if the response is not successful
                }
            }

            // onFailure is called when the API request fails
            override fun onFailure(call: Call<List<Quote>>, t: Throwable) {
                Log.e("MoodFragment", "Error fetching quote", t)  // Log the error
                quoteTextView.text = getString(R.string.error_retrieving_quote)  // Display an error message in the TextView
            }
        })
    }

    // Companion object to create a new instance of MoodFragment with the specified mood
    companion object {
        @JvmStatic
        fun newInstance(mood: String) =
            MoodFragment().apply {
                arguments = Bundle().apply {
                    putString("USER_MOOD", mood)  // Pass the mood to the fragment as an argument
                }
            }
    }
}
