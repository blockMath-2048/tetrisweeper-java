import java.util.ArrayList;
import java.util.Random;

public class GameController extends GameScript {

    public BoardController board;

    public AudioController audioController;

    public InputController input;

    public OverlayAnimationController overlay;

    public ArrayList<MenuItem> menuItems;

    public int currentPiece;

    public int currentRot;

    public byte currentMines;

    public byte currentReveals;

    public Vector2Int currentPos;

    public int heldPiece;

    public byte heldMines;

    public byte heldReveals;

    private int tmpPiece;

    private int tmpRot;

    private byte tmpMines;

    private byte tmpReveals;

    public PiecePreview heldPreview;

    public PiecePreview nextPreview;

    public ArrayList<Integer> pieceQueue;

    public int clearStreak;

    public int pieceRandMode;

    public float mineThreshold;

    public float autoDropTimer;

    public int lastMouseL;

    public int lastMouseR;

    public static final Vector2Int[][][] pieceData = new Vector2Int[][][] {
        new Vector2Int[][]
        {
            new Vector2Int[]
            {
                new Vector2Int(0, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 0)
            },
            new Vector2Int[]
            {
                new Vector2Int(0, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 0)
            },
            new Vector2Int[]
            {
                new Vector2Int(0, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 0)
            },
            new Vector2Int[]
            {
                new Vector2Int(0, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 0)
            }
        },
        new Vector2Int[][]
        {
            new Vector2Int[]
            {
                new Vector2Int(-1, 1),
                        new Vector2Int(0, 1),
                        new Vector2Int(0, 0),
                        new Vector2Int(1, 0)
            },
            new Vector2Int[]
            {
                new Vector2Int(1, 1),
                        new Vector2Int(1, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, -1)
            },
            new Vector2Int[]
            {
                new Vector2Int(1, -1),
                        new Vector2Int(0, -1),
                        new Vector2Int(0, 0),
                        new Vector2Int(-1, 0)
            },
            new Vector2Int[]
            {
                new Vector2Int(-1, -1),
                        new Vector2Int(-1, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 1)
            }
        },
        new Vector2Int[][]
        {
            new Vector2Int[]
            {
                new Vector2Int(-1, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(1, 0),
                        new Vector2Int(1, 1)
            },
            new Vector2Int[]
            {
                new Vector2Int(0, 1),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, -1),
                        new Vector2Int(1, -1)
            },
            new Vector2Int[]
            {
                new Vector2Int(1, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(-1, 0),
                        new Vector2Int(-1, -1)
            },
            new Vector2Int[]
            {
                new Vector2Int(0, -1),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 1),
                        new Vector2Int(-1, 1)
            }
        },
        new Vector2Int[][]
        {
            new Vector2Int[]
            {
                new Vector2Int(0, 0),
                        new Vector2Int(0, 1),
                        new Vector2Int(1, 1),
                        new Vector2Int(1, 0)
            },
            new Vector2Int[]
            {
                new Vector2Int(0, 1),
                        new Vector2Int(1, 1),
                        new Vector2Int(1, 0),
                        new Vector2Int(0, 0)
            },
            new Vector2Int[]
            {
                new Vector2Int(1, 1),
                        new Vector2Int(1, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 1)
            },
            new Vector2Int[]
            {
                new Vector2Int(1, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 1),
                        new Vector2Int(1, 1)
            }
        },
        new Vector2Int[][]
        {
            new Vector2Int[]
            {
                new Vector2Int(-1, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 1),
                        new Vector2Int(1, 1)
            },
            new Vector2Int[]
            {
                new Vector2Int(0, 1),
                        new Vector2Int(0, 0),
                        new Vector2Int(1, 0),
                        new Vector2Int(1, -1)
            },
            new Vector2Int[]
            {
                new Vector2Int(1, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, -1),
                        new Vector2Int(-1, -1)
            },
            new Vector2Int[]
            {
                new Vector2Int(0, -1),
                        new Vector2Int(0, 0),
                        new Vector2Int(-1, 0),
                        new Vector2Int(-1, 1)
            }
        },
        new Vector2Int[][]
        {
            new Vector2Int[]
            {
                new Vector2Int(-1, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(1, 0),
                        new Vector2Int(2, 0)
            },
            new Vector2Int[]
            {
                new Vector2Int(1, 1),
                        new Vector2Int(1, 0),
                        new Vector2Int(1, -1),
                        new Vector2Int(1, -2)
            },
            new Vector2Int[]
            {
                new Vector2Int(2, -1),
                        new Vector2Int(1, -1),
                        new Vector2Int(0, -1),
                        new Vector2Int(-1, -1)
            },
            new Vector2Int[]
            {
                new Vector2Int(0, -2),
                        new Vector2Int(0, -1),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 1)
            }
        },
        new Vector2Int[][]
        {
            new Vector2Int[]
            {
                new Vector2Int(-1, 1),
                        new Vector2Int(-1, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(1, 0)
            },
            new Vector2Int[]
            {
                new Vector2Int(1, 1),
                        new Vector2Int(0, 1),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, -1)
            },
            new Vector2Int[]
            {
                new Vector2Int(1, -1),
                        new Vector2Int(1, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(-1, 0)
            },
            new Vector2Int[]
            {
                new Vector2Int(-1, -1),
                        new Vector2Int(0, -1),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 1)
            }
        },
        new Vector2Int[][]
        {
            new Vector2Int[]
            {
                new Vector2Int(-1, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(1, 0),
                        new Vector2Int(0, 1)
            },
            new Vector2Int[]
            {
                new Vector2Int(0, 1),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, -1),
                        new Vector2Int(1, 0)
            },
            new Vector2Int[]
            {
                new Vector2Int(1, 0),
                        new Vector2Int(0, 0),
                        new Vector2Int(-1, 0),
                        new Vector2Int(0, -1)
            },
            new Vector2Int[]
            {
                new Vector2Int(0, -1),
                        new Vector2Int(0, 0),
                        new Vector2Int(0, 1),
                        new Vector2Int(-1, 0)
            }
        }
    };

    public ArrayList<Integer> clearStreakBonus;

    public float streakFactor;

    public static float autoDropCooldown(int level)
    {
        if (level < 20 || level >= 29)
        {
            return (float) Math.floor(60f * Math.exp((0f - (float)level) / 7f) + 1f) / 60f;
        }
        return autoDropCooldown(19);
    }

    public static int level(int lines)
    {
        return lines / 10;
    }

    public static int levelMultiplier(int level)
    {
        return level + 1;
    }

    public int getStreakBonus(int streak)
    {
        if (streak < clearStreakBonus.size())
        {
            return clearStreakBonus.get(streak);
        }
        return (int)((float)clearStreakBonus.get(clearStreakBonus.size() - 1) * Math.pow(streakFactor, streak - (clearStreakBonus.size() - 1)));
    }

    public void Start()
    {
        board.tilePrevCleared = new boolean[board.boardSize.y][];
        for (int i = 0; i < board.boardSize.y; i++)
        {
            board.tilePrevCleared[i] = new boolean[board.boardSize.x];
            for (int j = 0; j < board.boardSize.x; j++)
            {
                board.tilePrevCleared[i][j] = false;
            }
        }
        input.left = 0;
        input.right = 0;
        input.down = 0;
        input.rotLeft = 0;
        input.rotRight = 0;
        input.hold = 0;
        currentPiece = 0;
        heldPiece = 0;
        board.time = 0f;
        board.lines = 0;
        board.level = 0;
        board.clearBoard();
        board.tilemap.playerAlive = true;
        pieceQueue = new ArrayList<Integer>();
        audioController.playEffect(0);
        nextPiece();
    }

    public void Update(float deltaTime, Main main)
    {
        switch (/*menuItems.get(1).selection*/0)
        {
            case 0:
                pieceRandMode = 7;
                break;
            case 1:
                pieceRandMode = 9;
                break;
            case 2:
                pieceRandMode = 1;
                break;
        }
        mineThreshold = /*(float)menuItems.get(2).selection / 20f;*/ 0.1f;
        if ((mineThreshold < 0.1f || mineThreshold > 0.9f) && board.bhac < 1000000)
        {
            board.bhac = (int)System.currentTimeMillis() % 65536 + 65536;
        }
        board.level = level(board.lines);
        /*if (board.level < menuItems.get(3).selection)
        {
            board.level = menuItems.get(3).selection;
        }*/
        input.mouseControl = /*menuItems.get(4).selection == 0*/ true;
        audioController.musicActive = /*menuItems.get(5).selection == 0*/ true;
        board.tilePrevCleared = new boolean[board.boardSize.y][];
        for (int i = 0; i < board.boardSize.y; i++)
        {
            board.tilePrevCleared[i] = new boolean[board.boardSize.x];
            for (int j = 0; j < board.boardSize.x; j++)
            {
                board.tilePrevCleared[i][j] = false;
            }
        }
        if (!board.tilemap.playerAlive || board.tilemap.paused)
        {
            return;
        }
        board.time += deltaTime;
        if (board.time >= 5999f)
        {
            board.time = 5999f;
        }
        if (input.reveal < lastMouseL && input.selectionPos.x >= 0 && input.selectionPos.x < board.boardSize.x && input.selectionPos.y >= 0 && input.selectionPos.y < board.boardSize.y)
        {
            removePiece();
            if ((board.board[input.selectionPos.y][input.selectionPos.x] & 0x7F) < 8 && board.board[input.selectionPos.y][input.selectionPos.x] != 0)
            {
                revealAt(input.selectionPos);
            }
            placePiece();
        }
        if (input.flag > lastMouseR && input.selectionPos.x >= 0 && input.selectionPos.x < board.boardSize.x && input.selectionPos.y >= 0 && input.selectionPos.y < board.boardSize.y)
        {
            removePiece();
            if ((board.board[input.selectionPos.y][input.selectionPos.x] & 0x7F) < 16 && board.board[input.selectionPos.y][input.selectionPos.x] != 0)
            {
                if ((board.board[input.selectionPos.y][input.selectionPos.x] & 0x7F) < 8)
                {
                    board.board[input.selectionPos.y][input.selectionPos.x] += 8;
                    audioController.playEffect(2);
                }
                else
                {
                    board.board[input.selectionPos.y][input.selectionPos.x] -= 8;
                }
            }
            placePiece();
        }
        lastMouseL = input.reveal;
        lastMouseR = input.flag;

        this.FixedUpdate(deltaTime);
    }

    private void removePiece()
    {
        for (int i = 0; i < 4; i++)
        {
            Vector2Int vector2Int = Vector2Int.add(pieceData[currentPiece][currentRot][i], currentPos);
            if ((board.board[vector2Int.y][vector2Int.x] & 0x7F) >= 16)
            {
                currentReveals |= (byte)(1 << i);
            }
            board.board[vector2Int.y][vector2Int.x] = 0;
        }
    }

    private void placePiece()
    {
        for (int i = 0; i < 4; i++)
        {
            Vector2Int vector2Int = Vector2Int.add(pieceData[currentPiece][currentRot][i], currentPos);
            board.board[vector2Int.y][vector2Int.x] = (byte)(currentPiece + (((currentMines & (1 << i)) != 0) ? 128 : 0) + (((currentReveals & (1 << i)) != 0) ? 16 : 0));
        }
    }

    private boolean canPlacePiece()
    {
        for (int i = 0; i < 4; i++)
        {
            Vector2Int vector2Int = Vector2Int.add(pieceData[currentPiece][currentRot][i], currentPos);
            if (vector2Int.x < 0 || vector2Int.x >= board.boardSize.x || vector2Int.y < 0 || vector2Int.y >= board.boardSize.y)
            {
                return false;
            }
            if (board.board[vector2Int.y][vector2Int.x] != 0)
            {
                return false;
            }
        }
        return true;
    }

    private boolean tryMove(Vector2Int delta)
    {
        removePiece();
        currentPos = Vector2Int.add(currentPos, delta);
        if (canPlacePiece())
        {
            placePiece();
            return true;
        }
        currentPos = Vector2Int.sub(currentPos, delta);
        placePiece();
        return false;
    }

    private boolean tryRotation()
    {
        Vector2Int[] array = new Vector2Int[] {
            new Vector2Int(0, 0),
                    new Vector2Int(0, 0),
                    new Vector2Int(0, 0),
                    new Vector2Int(0, 0),
                    new Vector2Int(0, 0)
        };
        if (currentPiece != 3)
        {
            if (currentPiece == 5)
            {
                if ((currentRot == 1 && tmpRot == 0) || (currentRot == 2 && tmpRot == 3))
                {
                    array = new Vector2Int[] {
                        new Vector2Int(0, 0),
                                new Vector2Int(-2, 0),
                                new Vector2Int(1, 0),
                                new Vector2Int(-2, -1),
                                new Vector2Int(1, 2)
                    };
                }
                else if ((currentRot == 0 && tmpRot == 1) || (currentRot == 3 && tmpRot == 2))
                {
                    array = new Vector2Int[] {
                        new Vector2Int(0, 0),
                                new Vector2Int(2, 0),
                                new Vector2Int(-1, 0),
                                new Vector2Int(2, 1),
                                new Vector2Int(-1, -2)
                    };
                }
                else if ((currentRot == 2 && tmpRot == 1) || (currentRot == 3 && tmpRot == 0))
                {
                    array = new Vector2Int[] {
                        new Vector2Int(0, 0),
                                new Vector2Int(-1, 0),
                                new Vector2Int(2, 0),
                                new Vector2Int(-1, 2),
                                new Vector2Int(2, -1)
                    };
                }
                else if ((currentRot == 1 && tmpRot == 2) || (currentRot == 0 && tmpRot == 3))
                {
                    array = new Vector2Int[] {
                        new Vector2Int(0, 0),
                                new Vector2Int(1, 0),
                                new Vector2Int(-2, 0),
                                new Vector2Int(1, -2),
                                new Vector2Int(-2, 1)
                    };
                }
            }
            else if (currentPiece != 0)
            {
                if (currentRot == 1)
                {
                    array = new Vector2Int[] {
                        new Vector2Int(0, 0),
                                new Vector2Int(-1, 0),
                                new Vector2Int(-1, 1),
                                new Vector2Int(0, -2),
                                new Vector2Int(-1, -2)
                    };
                }
                else if (tmpRot == 1)
                {
                    array = new Vector2Int[] {
                        new Vector2Int(0, 0),
                                new Vector2Int(1, 0),
                                new Vector2Int(1, -1),
                                new Vector2Int(0, 2),
                                new Vector2Int(1, 2)
                    };
                }
                else if (currentRot == 3)
                {
                    array = new Vector2Int[] {
                        new Vector2Int(0, 0),
                                new Vector2Int(1, 0),
                                new Vector2Int(1, 1),
                                new Vector2Int(0, -2),
                                new Vector2Int(1, -2)
                    };
                }
                else if (tmpRot == 3)
                {
                    array = new Vector2Int[] {
                        new Vector2Int(0, 0),
                                new Vector2Int(-1, 0),
                                new Vector2Int(-1, -1),
                                new Vector2Int(0, 2),
                                new Vector2Int(-1, 2)
                    };
                }
            }
        }
        for (int i = 0; i < 5; i++)
        {
            currentPos = Vector2Int.add(currentPos, array[i]);
            if (canPlacePiece())
            {
                return true;
            }
            currentPos = Vector2Int.sub(currentPos, array[i]);
        }
        return false;
    }

    private void nextPiece()
    {
        if (pieceQueue.size() == 0)
        {
            switch (pieceRandMode)
            {
                case 0:
                    pieceQueue.add(3);
                    break;
                case 1:
                    pieceQueue.add((int)(Math.random() * 7 + 1));
                    break;
                default:
                {
                    int num = pieceRandMode;
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(1);
                    list.add(2);
                    list.add(3);
                    list.add(4);
                    list.add(5);
                    list.add(6);
                    list.add(7);
                    for (int i = 7; i < num; i++)
                    {
                        list.add((int)(Math.random() * 7 + 1));
                    }
                    for (int j = 0; j < num; j++)
                    {
                        int index = (int)(Math.random() * (list.size()));
                        pieceQueue.add(list.get(index));
                        list.remove(index);
                    }
                    break;
                }
            }
        }
        currentPiece = pieceQueue.get(0);
        pieceQueue.remove(0);
        if (pieceQueue.size() == 0)
        {
            switch (pieceRandMode)
            {
                case 0:
                    pieceQueue.add(3);
                    break;
                case 1:
                    pieceQueue.add((int)(Math.random() * 7 + 1));
                    break;
                default:
                {
                    int num2 = pieceRandMode;
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(1);
                    list.add(2);
                    list.add(3);
                    list.add(4);
                    list.add(5);
                    list.add(6);
                    list.add(7);
                    for (int k = 7; k < num2; k++)
                    {
                        list.add((int)(Math.random() * 7 + 1));
                    }
                    for (int l = 0; l < num2; l++)
                    {
                        int index2 = (int)(Math.random() * (list.size()));
                        pieceQueue.add(list.get(index2));
                        list.remove(index2);
                    }
                    break;
                }
            }
        }
        currentRot = 0;
        currentMines = 0;
        for (int m = 0; m < 4; m++)
        {
            boolean flag = Math.random() <= mineThreshold;
            currentMines = (byte)((int)(currentMines << 1) | (flag ? 1 : 0));
        }
        currentReveals = 0;
        currentPos = new Vector2Int(board.boardSize.x / 2 - 1, board.boardSize.y - 5);
        placePiece();
    }

    private void onInput(InputKey key)
    {
        switch (key)
        {
            case LEFT:
                tryMove(new Vector2Int(-1, 0));
                break;
            case RIGHT:
                tryMove(new Vector2Int(1, 0));
                break;
            case DOWN:
            case AUTODOWN:
            {
                if (tryMove(new Vector2Int(0, -1)))
                {
                    break;
                }
                clearStreak = 0;
                boolean flag = false;
                for (int i = 0; i < 4; i++)
                {
                    if (Vector2Int.add(pieceData[currentPiece][currentRot][i], currentPos).y < board.boardSize.y - 5)
                    {
                        flag = true;
                        break;
                    }
                }
                if (flag)
                {
                    nextPiece();
                    break;
                }
                board.tilemap.playerAlive = false;
                audioController.playEffect(1);
                break;
            }
            case ROTLEFT:
                removePiece();
                tmpRot = currentRot;
                currentRot = (currentRot - 1) & 3;
                if (!tryRotation())
                {
                    currentRot = tmpRot;
                }
                placePiece();
                break;
            case ROTRIGHT:
                removePiece();
                tmpRot = currentRot;
                currentRot = (currentRot + 1) & 3;
                if (!tryRotation())
                {
                    currentRot = tmpRot;
                }
                placePiece();
                break;
            case HOLD:
                removePiece();
                tmpPiece = currentPiece;
                tmpMines = currentMines;
                tmpReveals = currentReveals;
                tmpRot = currentRot;
                currentPiece = heldPiece;
                currentMines = heldMines;
                currentReveals = heldReveals;
                currentRot = 0;
                heldPiece = tmpPiece;
                heldMines = tmpMines;
                heldReveals = tmpReveals;
                if (currentPiece == 0)
                {
                    nextPiece();
                    break;
                }
                while (!tryRotation())
                {
                    currentRot++;
                    if (currentRot >= 4)
                    {
                        heldPiece = currentPiece;
                        heldMines = currentMines;
                        heldReveals = currentReveals;
                        currentPiece = tmpPiece;
                        currentMines = tmpMines;
                        currentReveals = tmpReveals;
                        currentRot = tmpRot;
                        break;
                    }
                }
                placePiece();
                break;
            case NONE:
                break;
        }
    }

    private void revealAt(Vector2Int pos)
    {
        if (pos.x < 0 || pos.x >= board.boardSize.x || pos.y < 0 || pos.y >= board.boardSize.y || board.tilePrevCleared[pos.y][pos.x])
        {
            return;
        }
        board.tilePrevCleared[pos.y][pos.x] = true;
        if ((board.board[pos.y][pos.x] & 0x7F) < 8 && board.board[pos.y][pos.x] != 0)
        {
            board.board[pos.y][pos.x] += 16;
            if (board.board[pos.y][pos.x] > 127)
            {
                board.tilemap.playerAlive = false;
                audioController.playEffect(1);
            }
        }
        if (board.getMineCount(pos.x, pos.y) == 0 && board.board[pos.y][pos.x] >= 16)
        {
            revealAt(Vector2Int.add(pos, new Vector2Int(-1, -1)));
            revealAt(Vector2Int.add(pos, new Vector2Int(0, -1)));
            revealAt(Vector2Int.add(pos, new Vector2Int(1, -1)));
            revealAt(Vector2Int.add(pos, new Vector2Int(-1, 0)));
            revealAt(Vector2Int.add(pos, new Vector2Int(1, 0)));
            revealAt(Vector2Int.add(pos, new Vector2Int(-1, 1)));
            revealAt(Vector2Int.add(pos, new Vector2Int(0, 1)));
            revealAt(Vector2Int.add(pos, new Vector2Int(1, 1)));
        }
    }

    private boolean tryClear(int row)
    {
        for (int i = 0; i < board.boardSize.x; i++)
        {
            byte b = board.board[row][i];
            if (b == 0)
            {
                return false;
            }
            if ((b & 0x7F) < 8)
            {
                return false;
            }
            if (b >= 8 && b < 16)
            {
                return false;
            }
        }
        audioController.playEffect(16 + ++clearStreak);
        board.lines++;
        board.addScore(getStreakBonus(clearStreak) * levelMultiplier(board.level));
        for (int j = row; j < board.boardSize.y; j++)
        {
            for (int k = 0; k < board.boardSize.x; k++)
            {
                if (j == board.boardSize.y - 1)
                {
                    board.board[j][k] = 0;
                }
                else
                {
                    board.board[j][k] = board.board[j + 1][k];
                }
            }
        }
        if (!canPlacePiece())
        {
            currentPos = Vector2Int.add(currentPos, new Vector2Int(0, -1));
        }
        return true;
    }

    private void FixedUpdate(float deltaTime)
    {
        board.tilePrevCleared = new boolean[board.boardSize.y][];
        for (int i = 0; i < board.boardSize.y; i++)
        {
            board.tilePrevCleared[i] = new boolean[board.boardSize.x];
            for (int j = 0; j < board.boardSize.x; j++)
            {
                board.tilePrevCleared[i][j] = false;
            }
        }
        if (board.tilemap.paused)
        {
            return;
        }
        if (input.pause > 0)
        {
            input.pause--;
            if (overlay.animationTimer == 1f && overlay.dir == 1)
            {
                board.tilemap.paused = true;
            }
        }
        if (!board.tilemap.playerAlive)
        {
            if (audioController.effectID == 0)
            {
                board.clearBoard();
                Start();
            }
            return;
        }
        if (currentPiece == 0)
        {
            nextPiece();
        }
        autoDropTimer += deltaTime;
        if (input.left > 0)
        {
            input.left--;
            onInput(InputKey.LEFT);
        }
        if (input.right > 0)
        {
            input.right--;
            onInput(InputKey.RIGHT);
        }
        if (input.down > 0)
        {
            input.down--;
            onInput(InputKey.DOWN);
        }
        if (input.rotLeft > 0)
        {
            input.rotLeft--;
            onInput(InputKey.ROTLEFT);
        }
        if (input.rotRight > 0)
        {
            input.rotRight--;
            onInput(InputKey.ROTRIGHT);
        }
        if (input.hold > 0)
        {
            input.hold--;
            onInput(InputKey.HOLD);
        }
        if (autoDropTimer >= autoDropCooldown(board.level))
        {
            autoDropTimer -= autoDropCooldown(board.level);
            onInput(InputKey.AUTODOWN);
        }
        /*for (int k = 0; k < heldPreview.boardSize.y; k++)
        {
            for (int l = 0; l < heldPreview.boardSize.x; l++)
            {
                heldPreview.board[k][l] = 0;
            }
        }
        if (heldPiece != 0)
        {
            for (int m = 0; m < 4; m++)
            {
                Vector2Int vector2Int = Vector2Int.add(pieceData[heldPiece][0][m], heldPreview.offsetPos);
                heldPreview.board[vector2Int.y][vector2Int.x] = (byte)(heldPiece + (((heldMines & (1 << m)) != 0) ? 128 : 0) + (((heldReveals & (1 << m)) != 0) ? 16 : 0));
            }
        }
        for (int n = 0; n < nextPreview.boardSize.y; n++)
        {
            for (int num = 0; num < nextPreview.boardSize.x; num++)
            {
                nextPreview.board[n][num] = 0;
            }
        }
        for (int num2 = 0; num2 < 4; num2++)
        {
            Vector2Int vector2Int2 = Vector2Int.add(pieceData[pieceQueue.get(0)][0][num2], nextPreview.offsetPos);
            nextPreview.board[vector2Int2.y][vector2Int2.x] = (byte)(int)pieceQueue.get(0);
        }*/
        for (int num3 = 0; num3 < board.boardSize.y; num3++)
        {
            for (int num4 = 0; num4 < board.boardSize.x; num4++)
            {
                if (board.getMineCount(num4, num3) == 0 && board.board[num3][num4] >= 16)
                {
                    revealAt(new Vector2Int(num4, num3));
                }
            }
        }
        removePiece();
        for (int num5 = 1; num5 < board.boardSize.y; num5++)
        {
            tryClear(num5);
        }
        placePiece();
    }
}
