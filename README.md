# HealMAC

Human-friendly Message Authentication Codes for Healthcare Services!

## Description

Sometimes it's necessary to validate the authenticity of messages as part of a process that involves human interaction. In these cases it's error-prone and cumbersome to use long Message Authentication Codes generatedy by the more intense cryptographic hashes (512 bits?!).

That's why Modern eHealth's open source group has created an alternative that is human-friendly enough for people to read the authentication codes over the telephone if needed.

The HealMAC library can be used to both generate and validate codes, which generated using the modern [Murmur3 hash function](https://en.wikipedia.org/wiki/MurmurHash). For maximum readability it is recommended to encode the MAC using something like [Base36](https://en.wikipedia.org/wiki/Base36) so that only familiar English alphabet letters and digits are presented to the user.
