# Bitcoin Core -> Multibit

This is just a tiny helper for importing private keys.

Requires:

- bitcoin installation new enough to have bitcoin-cli
- GNU toolchain (bash, grep, cut)

Caveats:

- I don't know what happens if your wallet is encrypted
- Don't do this on an unencrypted drive -- you're dumping private keys
  in the raw, so anyone with access to the drive can retrieve them
  later even if you delete them. Probably.
- This doesn't import your labels. See below for a way to at least get
  them into a more copyable format.

Let's do it:

```bash
bitcoind -daemon # start the daemon
bitcoin-cli dumpwallet wallet-dump.deleteme
grep label= wallet-dump.deleteme | cut -d' ' -f1,2,3 > import-into-multibit.key
```

...and then import that .key file into multibit.

Hey, maybe you wanted the labels printed next to the addresses so you
can copy them in more easily?

1. Grab [Leiningen](http://leiningen.org/) (the Clojure build tool)
2. From the Bitcoin Core receive addresses window, export to a CSV file.
3. `lein run path/to/addrs.csv`
