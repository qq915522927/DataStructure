IDIR =./include
CC=cc
CFLAGS=-I$(IDIR) -g -save-temps

ODIR=build
LDIR =../lib

LIBS=-lm
SRC=src

_DEPS = clist.h
DEPS = $(patsubst %,$(IDIR)/%,$(_DEPS))

_OBJ = clist.o chtbl.o linkedList.o
OBJ = $(patsubst %,$(ODIR)/%,$(_OBJ))
APP_ENTRY= example/$(APP)/main.o

main: $(OBJ) $(APP_ENTRY)

	$(CC) -g -o $@ $^ $(CFLAGS) $(LIBS)


$(ODIR)/%.o: $(SRC)/%.c
	$(CC) -g -save-temps -c -o $@ $< $(CFLAGS)

example/$(APP)/%.o: example/$(APP)/%.c
	$(CC) -save-temps  -c -o $@ $< $(CFLAGS)


.PHONY: clean

clean:
	rm -f $(ODIR)/*.o *~ core $(INCDIR)/*~ 