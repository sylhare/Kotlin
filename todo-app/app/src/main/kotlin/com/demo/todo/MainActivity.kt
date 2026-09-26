package com.demo.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { TodoApp() } }
    }
}

@Composable
fun TodoApp(viewModel: TodoViewModel = viewModel()) {
    val todos by viewModel.todos.collectAsState()
    TodoScreen(
        todos = todos,
        onAdd = viewModel::add,
        onToggle = viewModel::toggle,
        onRemove = viewModel::remove,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    todos: List<Todo>,
    onAdd: (String) -> Unit,
    onToggle: (Long) -> Unit,
    onRemove: (Long) -> Unit,
) {
    var text by rememberSaveable { mutableStateOf("") }
    Scaffold(topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(stringResource(R.string.new_todo)) },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("todo_input"),
                )
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = {
                        onAdd(text)
                        text = ""
                    },
                    modifier = Modifier.testTag("add_button"),
                ) { Text(stringResource(R.string.add)) }
            }
            LazyColumn(Modifier.fillMaxWidth()) {
                items(todos, key = { it.id }) { todo ->
                    TodoRow(todo, { onToggle(todo.id) }, { onRemove(todo.id) })
                }
            }
        }
    }
}

@Composable
private fun TodoRow(todo: Todo, onToggle: () -> Unit, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(value = todo.done, role = Role.Checkbox, onValueChange = { onToggle() }),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked = todo.done, onCheckedChange = null)
        Text(
            text = todo.text,
            modifier = Modifier.weight(1f),
            textDecoration = if (todo.done) TextDecoration.LineThrough else null,
        )
        TextButton(onClick = onRemove) { Text(stringResource(R.string.delete)) }
    }
}
