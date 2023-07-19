package com.example.recipefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipefinder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: RecipeSearchViewModel by viewModels()
    private val adapter: RecipeListAdapter by lazy { RecipeListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                viewModel.searchRecipes(query)
            }
        }

        viewModel.searchResults.observe(this, Observer { recipes ->
            adapter.submitList(recipes)
        })
    }

    private fun setupRecyclerView() {
        binding.rvRecipeList.layoutManager = LinearLayoutManager(this)
        binding.rvRecipeList.adapter = adapter
    }
}