package br.com.rabbithole.core.builder.commands.string.gets;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import br.com.rabbithole.core.builder.commands.generics.Keys;
import br.com.rabbithole.core.builder.commands.string.Get;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class GetAllByPrefix implements Command, Read, Execute<Map<String, String>> {
    private final String key;

    @Override
    public String commandName() {
        return "getAllByPrefix";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Optional<Map<String, String>> execute() {
        Optional<Set<String>> keys = new Keys.Builder().setKey(getKey() + ".*").execute();
        Map<String, String> result = new HashMap<>();

        if (RedisLib.inDebug())
            RedisLib.getLogger().info("Query: " + commandName() + " has executed!");

        keys.ifPresent(data -> {
            for (String key : data) {
                Optional<String> value = new Get.Builder().setKey(key).execute();
                value.ifPresent(resultValue -> result.put(key, resultValue));
            }
        });

        if (!result.isEmpty()) return Optional.of(result);
        return Optional.empty();
    }

    private GetAllByPrefix(Builder builder) {
        this.key = builder.key;
    }

    private Query<GetAllByPrefix> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Map<String, String>> {
        private String key;

        public Builder setPrefix(String prefix) {
            this.key = prefix;
            return this;
        }

        public Query<GetAllByPrefix> build() {
            return new GetAllByPrefix(this).query();
        }

        @Override
        public Optional<Map<String, String>> execute() {
            return build().getCommand().execute();
        }
    }
}
